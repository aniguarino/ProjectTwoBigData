package routesJobDistinct.functions;

import java.text.ParseException;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import routesJobDistinct.model.RouteIdWithoutCarrier;
import routesJobDistinct.model.RouteInfo;
import scala.Tuple2;

public class ManagingFlights implements PairFunction<Tuple2<Object, BSONObject>, RouteIdWithoutCarrier, RouteInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<RouteIdWithoutCarrier, RouteInfo> call(Tuple2<Object, BSONObject> arg) throws ParseException {
		String origin = (String) arg._2.get("Origin");
		String originCityName = (String) arg._2.get("OriginCityName");
		String dest = (String) arg._2.get("Dest");
		String destCityName = (String) arg._2.get("DestCityName");
		String flightDate = (String) arg._2.get("FlightDate");
		String distance = arg._2.get("Distance").toString();
		
		Long depDelay = (long)(double)arg._2.get("DepDelayMinutes");
		depDelay = depDelay * 60 * 1000;
		Long arrDelay = (long)(double)arg._2.get("ArrDelayMinutes");
		arrDelay = arrDelay * 60 * 1000;
		Integer countDelay = 1;
		
		// Si vanno ad escludere nel calcolo della media del tempo di volo i voli prima del 1996
		Long airTime = 0L;
		Integer countAirTime = 0;
		
		if(flightDate.compareTo("1996-01-01")>0){
			airTime = (long)(double)arg._2.get("ActualElapsedTime");
			airTime = airTime * 60 * 1000;
			countAirTime = 1;
		}

		return new Tuple2<RouteIdWithoutCarrier, RouteInfo>(new RouteIdWithoutCarrier(origin, dest), new RouteInfo(flightDate, flightDate, distance, airTime, countAirTime, depDelay, arrDelay, countDelay, originCityName, destCityName));
	}
}
