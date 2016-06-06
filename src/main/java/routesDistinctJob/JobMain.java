package routesDistinctJob;

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

import routesDistinctJob.functions.FilterAirport;
import routesDistinctJob.functions.FilterCancelledAndDiverted;
import routesDistinctJob.functions.ManagingAirportFunction;
import routesDistinctJob.functions.ManagingFlights;
import routesDistinctJob.functions.ProduceRoutes;
import routesDistinctJob.functions.SaveMongoCalcMean;
import routesDistinctJob.model.AirportInfo;
import routesDistinctJob.model.RouteId;
import routesDistinctJob.model.RouteInfo;


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

		Configuration outputConfig = new Configuration();
		outputConfig.set("mongo.output.uri", "mongodb://localhost:27017/airplaneDB.distinctroutes");
		
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

		JavaPairRDD<RouteId, RouteInfo> flights = inputRDD.mapToPair(new ManagingFlights());
		
		JavaPairRDD<RouteId, RouteInfo> routes = flights.reduceByKey(new ProduceRoutes());
		
		JavaPairRDD<Object, BSONObject> routesSave = routes.mapToPair(new SaveMongoCalcMean(airportsBroadcast));

		routesSave.saveAsNewAPIHadoopFile(
				"file:///this-is-completely-unused",
				Object.class,
				BSONObject.class,
				MongoOutputFormat.class,
				outputConfig
				);

		System.out.println("Fatto! Ho salvato le rotte distinte in MongoDB...");
	}
}
