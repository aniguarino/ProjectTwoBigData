package meanDelay_StandardDeviationJob;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.bson.BSONObject;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;

import meanDelay_StandardDeviationJob.functions.ManagingRoutes;
import meanDelay_StandardDeviationJob.functions.ProduceDelays;
import meanDelay_StandardDeviationJob.functions.ProduceIntermStandDeviation;
import meanDelay_StandardDeviationJob.functions.ManagingIntermStandDeviation;
import meanDelay_StandardDeviationJob.functions.SaveMongo;
import meanDelay_StandardDeviationJob.model.*;
import routesJob.functions.FilterCancelledAndDiverted;

public class JobMain {
	private static JavaSparkContext sc;

	public static void main( String[] args ){
		SparkConf sparkConf = new SparkConf().setAppName("meanDelayJob").setMaster("local");
		sc = new JavaSparkContext(sparkConf);

		Configuration inputConfig = new Configuration();
		inputConfig.set("mongo.input.uri", "mongodb://localhost:27017/airplaneDB.input");

		Configuration outputConfig = new Configuration();
		outputConfig.set("mongo.output.uri", "mongodb://localhost:27017/airplaneDB.meanDelay_StandardDeviation");

		JavaPairRDD<Object, BSONObject> inputRDD = sc.newAPIHadoopRDD(
				inputConfig,       // Configuration
				MongoInputFormat.class,   // InputFormat: read from a live cluster.
				Object.class,             // Key class
				BSONObject.class          // Value class
				).filter(new FilterCancelledAndDiverted());

		JavaPairRDD<FlightId, FlightInfoDelay> flights = inputRDD.mapToPair(new ManagingRoutes());
		
		JavaPairRDD<FlightId, FlightInfoDelay> delays = flights.reduceByKey(new ProduceDelays());
		
		JavaPairRDD<FlightId, FlightInfoDelay> MapStandardDeviation = delays.mapToPair(new ManagingIntermStandDeviation());
		
		JavaPairRDD<FlightId, FlightInfoDelay> StandardDeviation = MapStandardDeviation.reduceByKey(new ProduceIntermStandDeviation());
		
		JavaPairRDD<Object, BSONObject> meanDelay_StandardDeviationSave = StandardDeviation.mapToPair(new SaveMongo());

		meanDelay_StandardDeviationSave.saveAsNewAPIHadoopFile(
				"file:///this-is-completely-unused",
				Object.class,
				BSONObject.class,
				MongoOutputFormat.class,
				outputConfig
				);

		System.out.println("Fatto! Ho salvato i ritardi medi e le deviazioni standard con granularita' fine, in MongoDB...");
	}
}
