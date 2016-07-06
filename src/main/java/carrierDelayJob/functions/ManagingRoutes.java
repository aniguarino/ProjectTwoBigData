package carrierDelayJob.functions;

import java.text.ParseException;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import carrierDelayJob.model.CarrierDelay;
import scala.Tuple2;

public class ManagingRoutes implements PairFunction<Tuple2<Object, BSONObject>, String, CarrierDelay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<String, CarrierDelay> call(Tuple2<Object, BSONObject> arg) throws ParseException {
		Double carrierDelay = (Double) arg._2.get("CarrierDelay");
		Double weatherDelay = (Double) arg._2.get("WeatherDelay");
		Double NASDelay = (Double) arg._2.get("NASDelay");
		Double securityDelay = (Double) arg._2.get("SecurityDelay");
		Double lateAircraftDelay = (Double) arg._2.get("LateAircraftDelay");
		
		CarrierDelay delay = new CarrierDelay(carrierDelay, weatherDelay, NASDelay, securityDelay, lateAircraftDelay);
		
		String uniqueCarrier = (String) arg._2.get("UniqueCarrier");
		
		return new Tuple2<String, CarrierDelay>(uniqueCarrier, delay);
	}
}
