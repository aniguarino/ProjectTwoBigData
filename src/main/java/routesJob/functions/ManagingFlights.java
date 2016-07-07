package routesJob.functions;

import java.text.ParseException;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import routesJob.model.FlightInfoDelay;
import routesJob.model.RouteId;
import routesJob.model.RouteInfo;
import scala.Tuple2;

public class ManagingFlights implements PairFunction<Tuple2<Object, BSONObject>, RouteId, RouteInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<RouteId, RouteInfo> call(Tuple2<Object, BSONObject> arg) throws ParseException {
		String origin = (String) arg._2.get("Origin");
		String originCityName = (String) arg._2.get("OriginCityName");
		String dest = (String) arg._2.get("Dest");
		String destCityName = (String) arg._2.get("DestCityName");
		String flightDate = (String) arg._2.get("FlightDate");
		String uniqueCarrier = (String) arg._2.get("UniqueCarrier");
		String distance = arg._2.get("Distance").toString();
		
		Double depDelay = (double)arg._2.get("DepDelayMinutes");
		Double arrDelay = (double)arg._2.get("ArrDelayMinutes");
		Integer countDelay = 1;

		Double carrierDelay = 0.0;
		Double weatherDelay = 0.0;
		Double NASDelay = 0.0;
		Double securityDelay = 0.0;
		Double lateAircraftDelay = 0.0;
		
		if(!"".equals(arg._2.get("CarrierDelay"))){
			carrierDelay = (Double)arg._2.get("CarrierDelay");
		}
		if(!"".equals(arg._2.get("WeatherDelay"))){
			weatherDelay = (Double)arg._2.get("WeatherDelay");
		}
		if(!"".equals(arg._2.get("NASDelay"))){
			NASDelay = (Double)arg._2.get("NASDelay");
		}
		if(!"".equals(arg._2.get("SecurityDelay"))){
			securityDelay = (Double)arg._2.get("SecurityDelay");
		}
		if(!"".equals(arg._2.get("LateAircraftDelay"))){
			lateAircraftDelay = (Double)arg._2.get("LateAircraftDelay");
		}
		
		FlightInfoDelay infoDelay = new FlightInfoDelay(depDelay, arrDelay, carrierDelay, weatherDelay, NASDelay, securityDelay, lateAircraftDelay, countDelay);
		
		// Si vanno ad escludere nel calcolo della media del tempo di volo i voli prima del 1996
		Long airTime = 0L;
		Integer countAirTime = 0;
		
		if(flightDate.compareTo("1996-01-01")>0){
			airTime = (long)(double)arg._2.get("ActualElapsedTime");
			countAirTime = 1;
		}

		return new Tuple2<RouteId, RouteInfo>(new RouteId(origin, dest, uniqueCarrier), new RouteInfo(flightDate, flightDate, distance, airTime, countAirTime, infoDelay, originCityName, destCityName));
	}
}
