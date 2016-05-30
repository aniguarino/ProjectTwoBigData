package meanDelayStandardDeviationJob;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.bson.BSONObject;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;

import meanDelayStandardDeviationJob.functions.ProduceMeanDelays;
import meanDelayStandardDeviationJob.functions.ProduceStandardDeviation;
import meanDelayStandardDeviationJob.functions.ManagingRoutes;
import meanDelayStandardDeviationJob.functions.ProduceDelays;
import meanDelayStandardDeviationJob.functions.ProduceDeviation;
import meanDelayStandardDeviationJob.functions.SaveMongo;
import meanDelayStandardDeviationJob.model.*;
import routesJob.functions.FilterCancelledAndDiverted;
import scala.Tuple2;

public class JobMain {
	private static JavaSparkContext sc;

	public static void main( String[] args ){
		SparkConf sparkConf = new SparkConf().setAppName("meanDelayJob").setMaster("local");
		sc = new JavaSparkContext(sparkConf);

		Configuration inputConfig = new Configuration();
		inputConfig.set("mongo.input.uri", "mongodb://localhost:27017/airplaneDB.input");

		Configuration outputConfig = new Configuration();
		outputConfig.set("mongo.output.uri", "mongodb://localhost:27017/airplaneDB.meanDelayStandardDeviation");

		JavaPairRDD<Object, BSONObject> inputRDD = sc.newAPIHadoopRDD(
				inputConfig,       // Configuration
				MongoInputFormat.class,   // InputFormat: read from a live cluster.
				Object.class,             // Key class
				BSONObject.class          // Value class
				).filter(new FilterCancelledAndDiverted());

		JavaPairRDD<FlightId, FlightInfoDelay> flights = inputRDD.mapToPair(new ManagingRoutes()).cache();
		
		JavaPairRDD<FlightId, FlightInfoDelay> delays = flights.reduceByKey(new ProduceDelays());
		
		JavaPairRDD<FlightId, FlightInfoDelay> meanDelays = delays.mapToPair(new ProduceMeanDelays());
		
		//Join between meanDelays and flights
		JavaPairRDD<FlightId, Tuple2<FlightInfoDelay, FlightInfoDelay>> join = flights.join(meanDelays);
		
		JavaPairRDD<FlightId, FlightInfoDelay> produceDeviation = join.mapToPair(new ProduceDeviation());
		
		JavaPairRDD<FlightId, FlightInfoDelay> standardDeviation = produceDeviation.reduceByKey(new ProduceStandardDeviation());
		
		JavaPairRDD<Object, BSONObject> meanDelayStandardDeviationSave = standardDeviation.mapToPair(new SaveMongo());

		meanDelayStandardDeviationSave.saveAsNewAPIHadoopFile(
				"file:///this-is-completely-unused",
				Object.class,
				BSONObject.class,
				MongoOutputFormat.class,
				outputConfig
				);

		System.out.println("Fatto! Ho salvato i ritardi medi e le deviazioni standard con granularita' fine, in MongoDB...");
	}
}
