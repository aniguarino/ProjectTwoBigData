package routesJob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.hadoop.MongoInputFormat;

import scala.Tuple2;


public class JobMain 
{
    public static void main( String[] args ){
    	
    	SparkConf sparkConf = new SparkConf().setAppName("routesJob").setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		final Map<String, airportInfo> airports = new HashMap<>();
		
		Configuration mongodbConfigAirports = new Configuration();
		mongodbConfigAirports.set("mongo.input.uri", "mongodb://localhost:27017/airplaneDB.airports");
		
		Configuration mongodbConfigInput = new Configuration();
		mongodbConfigInput.set("mongo.input.uri", "mongodb://localhost:27017/airplaneDB.input");
		
		/*
		Configuration outputConfig = new Configuration();
		outputConfig.set("mongo.output.uri",
		                 "mongodb://localhost:27017/airplaneDB.routes");
		*/
		
		JavaPairRDD<Object, BSONObject> airportsRDD = sc.newAPIHadoopRDD(
			    mongodbConfigAirports,            // Configuration
			    MongoInputFormat.class,   // InputFormat: read from a live cluster.
			    Object.class,             // Key class
			    BSONObject.class          // Value class
			);
		
		JavaRDD<String> mapAirports = airportsRDD.flatMap(new FlatMapFunction<Tuple2<Object, BSONObject>, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Iterable<String> call(Tuple2<Object, BSONObject> arg) {
				Object iata = arg._2.get("iata_code");
				Object name = arg._2.get("name");
				Object latitude = arg._2.get("latitude_deg");
				Object longitude = arg._2.get("longitude_deg");
				if (iata instanceof String) {
					String iataString = (String) iata;
					String nameString = (String) name;
					Double latitudeDouble = (Double) latitude;
					Double longitudeDouble = (Double) longitude;
					return Arrays.asList(iataString, nameString, String.valueOf(latitudeDouble), String.valueOf(longitudeDouble));
				} else {
					return Collections.emptyList();
				}
			}
		});
		
		//Setting up airports Map
		List<String> result = mapAirports.collect();
		for(int i=0; i<result.size(); i++){
			airports.put(result.get(i), new airportInfo(result.get(i+1),Double.parseDouble(result.get(i+2)),Double.parseDouble(result.get(i+3))));
			i=i+3;
		}
		
		JavaPairRDD<Object, BSONObject> inputRDD = sc.newAPIHadoopRDD(
			    mongodbConfigInput,            // Configuration
			    MongoInputFormat.class,   // InputFormat: read from a live cluster.
			    Object.class,             // Key class
			    BSONObject.class          // Value class
			);
		
		
		//Make map of airports serializable! I can't access to field of airportInfo dinamycally into JavaRDD mapRoutes
		final Map<String, String> airportsSerial = new HashMap<>();
		for(Entry<String, airportInfo> entry : airports.entrySet()) {
		    String key = entry.getKey();
		    airportInfo value = entry.getValue();
		    airportsSerial.put(new String(key), new String(value.getLatitude()+";"+value.getLongitude()));
		}
		
		
		JavaRDD<String> mapRoutes = inputRDD.flatMap(new FlatMapFunction<Tuple2<Object, BSONObject>, String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Iterable<String> call(Tuple2<Object, BSONObject> arg) {
				Object origin = arg._2.get("Origin");
				//Object originCityName = arg._2.get("OriginCityName");
				Object dest = arg._2.get("Dest");
				//Object destCityName = arg._2.get("DestCityName");
				Object flightDate = arg._2.get("FlightDate");
			
				
				if (origin instanceof String) {
					String originString = (String) origin;
					//String originCityNameString = (String) originCityName;
					String destString = (String) dest;
					//String destCityNameString = (String) destCityName;
					String flightDateString = (String) flightDate; 
					
					
					String latLongOrigin = airportsSerial.get(originString);
					
					String latLongDest = airportsSerial.get(destString);
					
					return Arrays.asList(flightDateString, originString, /*originCityNameString,*/ latLongOrigin,
							destString, /*destCityNameString,*/ latLongDest);
				} else {
					return Collections.emptyList();
				}
			}
		});
		
		//System.out.println(mapRoutes.toArray().toString());
		
		//Save the routes in mongo at collection routes of airplaneDB
		
		// To connect to mongodb server
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // Now connect to your databases
        @SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("airplaneDB");
        //Create collection
        DBCollection coll = db.createCollection("routes", null);
        
        //Insert routes
        
        ArrayList<String> finalInsert = (ArrayList<String>) mapRoutes.collect();
        
        //StringTokenizer tokenizer1;
        //StringTokenizer tokenizer2;
        
        System.out.println("Salvataggio in corso in MongoDB...Attendere...");
        for(int i=0; i<finalInsert.size(); i++){
 
        	//tokenizer1 = new StringTokenizer(finalInsert.get(i+2),";");
        	//tokenizer2 = new StringTokenizer(finalInsert.get(i+4),";");
        	
        	BasicDBObject route = new BasicDBObject().
                    append("FlightDate", finalInsert.get(i)).
                    append("Origin", finalInsert.get(i+1)).
                    //append("OriginLatitude", Double.parseDouble(tokenizer1.nextToken())).
                    //append("OriginLongitude", Double.parseDouble(tokenizer1.nextToken())).
                    append("OriginCoordinates", finalInsert.get(i+2)).
		        	append("Dest", finalInsert.get(i+3)).
		        	//append("DestLatitude", Double.parseDouble(tokenizer2.nextToken())).
		        	//append("DestLongitude", Double.parseDouble(tokenizer2.nextToken())).
		            append("DestCoordinates", finalInsert.get(i+4));
        	
            coll.insert(route);
			
			i=i+4;
		}
        
        mongoClient.close();
        System.out.println("Esecuzione Terminata con successo...");
    }
}
