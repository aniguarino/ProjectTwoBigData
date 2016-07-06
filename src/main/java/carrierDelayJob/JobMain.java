package carrierDelayJob;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.bson.BSONObject;

import com.mongodb.hadoop.MongoInputFormat;
import com.mongodb.hadoop.MongoOutputFormat;

import carrierDelayJob.functions.ManagingRoutes;
import carrierDelayJob.functions.ProduceDelay;
import carrierDelayJob.functions.SaveMongo;
import carrierDelayJob.model.CarrierDelay;

public class JobMain {
	private static JavaSparkContext sc;

	public static void main( String[] args ){
		SparkConf sparkConf = new SparkConf().setAppName("carrierDelayJob").setMaster("local");
		sc = new JavaSparkContext(sparkConf);

		Configuration inputConfig = new Configuration();
		inputConfig.set("mongo.input.uri", "mongodb://localhost:27017/airplaneDB.routes");

		Configuration outputConfig = new Configuration();
		outputConfig.set("mongo.output.uri", "mongodb://localhost:27017/airplaneDB.carrierdelay");

		JavaPairRDD<Object, BSONObject> inputRDD = sc.newAPIHadoopRDD(
				inputConfig,       // Configuration
				MongoInputFormat.class,   // InputFormat: read from a live cluster.
				Object.class,             // Key class
				BSONObject.class          // Value class
				);

		JavaPairRDD<String, CarrierDelay> markers = inputRDD.mapToPair(new ManagingRoutes()).reduceByKey(new ProduceDelay());
		
		JavaPairRDD<Object, BSONObject> markersSave = markers.mapToPair(new SaveMongo());

		markersSave.saveAsNewAPIHadoopFile(
				"file:///this-is-completely-unused",
				Object.class,
				BSONObject.class,
				MongoOutputFormat.class,
				outputConfig
				);

		System.out.println("Fatto! Ho salvato i dettagli di ritardo per compagnia in MongoDB...");
	}
}
