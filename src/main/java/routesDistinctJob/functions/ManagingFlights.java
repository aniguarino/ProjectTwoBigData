package routesDistinctJob.functions;

import java.text.ParseException;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import routesDistinctJob.model.RouteId;
import routesDistinctJob.model.RouteInfo;
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
		String distance = arg._2.get("Distance").toString();
		
		// Si vanno ad escludere nel calcolo della media del tempo di volo i voli prima del 1996
		Long airTime = 0L;
		Integer count = 0;
		
		if(flightDate.compareTo("1996-01-01")>0){
			airTime = (long)(double)arg._2.get("ActualElapsedTime");
			airTime = airTime * 60 * 1000;
			count = 1;
		}

		return new Tuple2<RouteId, RouteInfo>(new RouteId(origin, dest), new RouteInfo(flightDate, flightDate, distance, airTime, count, originCityName, destCityName));
	}
}
