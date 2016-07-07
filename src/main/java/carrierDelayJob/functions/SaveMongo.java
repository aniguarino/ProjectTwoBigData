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
		Double sum = arg0._2.getCarrierDelay()+arg0._2.getWeatherDelay()+arg0._2.getNASDelay()+arg0._2.getSecurityDelay()+arg0._2.getLateAircraftDelay();
		
		Double carrierDelayPercent = arg0._2.getCarrierDelay()/sum*100;
		Double weatherDelayPercent = arg0._2.getWeatherDelay()/sum*100;
		Double NASDelayPercent = arg0._2.getNASDelay()/sum*100;
		Double securityDelayPercent = arg0._2.getSecurityDelay()/sum*100;
		Double lateAircraftDelayPercent = arg0._2.getLateAircraftDelay()/sum*100;
		
		BSONObject save = new BasicDBObject().
				append("UniqueCarrier", arg0._1).

				append("CarrierDelayPercent", carrierDelayPercent).
				append("WeatherDelayPercent", weatherDelayPercent).
				append("NASDelayPercent", NASDelayPercent).
				append("SecurityDelayPercent", securityDelayPercent).
				append("LateAircraftDelayPercent", lateAircraftDelayPercent);
		
		return new Tuple2<Object, BSONObject>(null, save);
	}
}