package carrierDelayJob.functions;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

import carrierDelayJob.model.CarrierDelay;
import scala.Tuple2;

public class SaveMongo implements PairFunction<Tuple2<String, CarrierDelay>, Object, BSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<Object, BSONObject> call(Tuple2<String, CarrierDelay> arg0) throws Exception {
		BSONObject save = new BasicDBObject().
				append("UniqueCarrier", arg0._1).
				
				append("CarrierDelay", arg0._2.getCarrierDelay()).
				append("WeatherDelay", arg0._2.getWeatherDelay()).
				append("NASDelay", arg0._2.getNASDelay()).
				append("SecurityDelay", arg0._2.getSecurityDelay()).
				append("LateAircraftDelay", arg0._2.getLateAircraftDelay()
				);
		
		return new Tuple2<Object, BSONObject>(null, save);
	}
}