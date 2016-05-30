package ghostFlightJob;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.bson.BSONObject;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;

import ghostFlightJob.functions.CountGhostFlight;
import ghostFlightJob.functions.CountGhostFlightForCompany;
import ghostFlightJob.functions.FilterAndPre2008;
import ghostFlightJob.functions.ManagingFlights;
import ghostFlightJob.functions.SaveMongoCalcMean;
import ghostFlightJob.functions.TrasformToCompany;
import ghostFlightJob.model.AirplaneId;
import ghostFlightJob.model.CompanyId;
import ghostFlightJob.model.Counters;
import ghostFlightJob.model.FlightInfo;


public class JobMain {
	private static JavaSparkContext sc;

	public static void main( String[] args ){
		SparkConf sparkConf = new SparkConf().setAppName("ghostFlightJob").setMaster("local");
		sc = new JavaSparkContext(sparkConf);

		Configuration inputConfig = new Configuration();
		inputConfig.set("mongo.input.uri", "mongodb://localhost:27017/airplaneDB.input");

		Configuration outputConfig = new Configuration();
		outputConfig.set("mongo.output.uri", "mongodb://localhost:27017/airplaneDB.ghostFlight");

		JavaPairRDD<Object, BSONObject> inputRDD = sc.newAPIHadoopRDD(
				inputConfig,       // Configuration
				MongoInputFormat.class,   // InputFormat: read from a live cluster.
				Object.class,             // Key class
				BSONObject.class          // Value class
				).filter(new FilterAndPre2008());

		JavaPairRDD<AirplaneId, Iterable<FlightInfo>> airplaneHistory = inputRDD.mapToPair(new ManagingFlights()).groupByKey();
		
		JavaPairRDD<AirplaneId, Counters> ghostFlightForAirplaneInMonth = airplaneHistory.mapToPair(new CountGhostFlight());
		
		JavaPairRDD<CompanyId, Counters> prepareGhostFlightForCompanyInMonth = ghostFlightForAirplaneInMonth.mapToPair(new TrasformToCompany());
		
		JavaPairRDD<CompanyId, Counters> ghostFlightForCompanyInMonth = prepareGhostFlightForCompanyInMonth.reduceByKey(new CountGhostFlightForCompany());
		
		JavaPairRDD<Object, BSONObject> routesSave = ghostFlightForCompanyInMonth.mapToPair(new SaveMongoCalcMean());

		routesSave.saveAsNewAPIHadoopFile(
				"file:///this-is-completely-unused",
				Object.class,
				BSONObject.class,
				MongoOutputFormat.class,
				outputConfig
				);

		System.out.println("Fatto! Ho salvato il conteggio dei voli fantasma (senza passeggeri) in MongoDB...");
	}
}
