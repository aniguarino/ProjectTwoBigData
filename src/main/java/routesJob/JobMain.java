package routesJob;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.bson.BSONObject;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;

import routesJob.functions.FilterAirport;
import routesJob.functions.FilterCancelledAndDiverted;
import routesJob.functions.ManagingAirportFunction;
import routesJob.functions.ManagingFlights;
import routesJob.functions.ManagingFlightsDistinct;
import routesJob.functions.ProduceRoutes;
import routesJob.functions.SaveMongoCalcMean;
import routesJob.functions.SaveMongoCalcMeanDistinct;
import routesJob.model.AirportInfo;
import routesJob.model.RouteId;
import routesJob.model.RouteIdWithoutCarrier;
import routesJob.model.RouteInfo;


public class JobMain {
	private static JavaSparkContext sc;

	public static void main( String[] args ){
		SparkConf sparkConf = new SparkConf().setAppName("routesJob").setMaster("local");
		sc = new JavaSparkContext(sparkConf);
		
		Map<String, AirportInfo> airports = new HashMap<String, AirportInfo>();

		Configuration mongodbConfigAirports = new Configuration();
		mongodbConfigAirports.set("mongo.input.uri", "mongodb://localhost:27017/airplaneDB.airports");

		Configuration inputConfig = new Configuration();
		inputConfig.set("mongo.input.uri", "mongodb://localhost:27017/airplaneDB.input");

		Configuration outputConfigRoutes = new Configuration();
		outputConfigRoutes.set("mongo.output.uri", "mongodb://localhost:27017/airplaneDB.routes");
		
		Configuration outputConfigRoutesDistinct = new Configuration();
		outputConfigRoutesDistinct.set("mongo.output.uri", "mongodb://localhost:27017/airplaneDB.distinctroutes");
		
		JavaPairRDD<Object, BSONObject> airportsRDD = sc.newAPIHadoopRDD(
				mongodbConfigAirports,            // Configuration
				MongoInputFormat.class,   // InputFormat: read from a live cluster.
				Object.class,             // Key class
				BSONObject.class          // Value class
				);

		airports = airportsRDD.mapToPair(new ManagingAirportFunction()).filter(new FilterAirport()).collectAsMap();
		
		final Broadcast<Map<String, AirportInfo>> airportsBroadcast = sc.broadcast(airports);

		JavaPairRDD<Object, BSONObject> inputRDD = sc.newAPIHadoopRDD(
				inputConfig,       // Configuration
				MongoInputFormat.class,   // InputFormat: read from a live cluster.
				Object.class,             // Key class
				BSONObject.class          // Value class
				).filter(new FilterCancelledAndDiverted());

		JavaPairRDD<RouteId, RouteInfo> routesAll = inputRDD.mapToPair(new ManagingFlights()).reduceByKey(new ProduceRoutes());
		
		JavaPairRDD<RouteId, RouteInfo> routesDistinct = inputRDD.mapToPair(new ManagingFlightsDistinct()).reduceByKey(new ProduceRoutes());
		
		JavaPairRDD<Object, BSONObject> routesSaveAll = routesAll.mapToPair(new SaveMongoCalcMean(airportsBroadcast));
		
		JavaPairRDD<Object, BSONObject> routesSaveDistinct = routesDistinct.mapToPair(new SaveMongoCalcMeanDistinct(airportsBroadcast));

		routesSaveAll.saveAsNewAPIHadoopFile(
				"file:///this-is-completely-unused",
				Object.class,
				BSONObject.class,
				MongoOutputFormat.class,
				outputConfigRoutes
				);
		
		routesSaveDistinct.saveAsNewAPIHadoopFile(
				"file:///this-is-completely-unused",
				Object.class,
				BSONObject.class,
				MongoOutputFormat.class,
				outputConfigRoutesDistinct
				);

		System.out.println("Fatto! Ho salvato le rotte in MongoDB...");
	}
}
