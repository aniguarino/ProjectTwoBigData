package routesJob.functions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

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
		String distance = (String) arg._2.get("Distance").toString();
		
		String depTime = arg._2.get("DepTime").toString();
		while(depTime.length()<4){
			depTime = "0".concat(depTime);
		}
		String arrTime = arg._2.get("ArrTime").toString();
		while(arrTime.length()<4){
			arrTime = "0".concat(arrTime);
		}
		
		DateFormat df = new SimpleDateFormat("HHmm");
		Date depTimeDate = df.parse(depTime);
		Date arrTimeDate = df.parse(arrTime);
		
		// Si vanno ad escludere nel calcolo della media del tempo di volo i voli prima del 2000
		Long airTime = 0L;
		Integer count = 0;
		
		if(flightDate.compareTo("2000-01-01")>0){
			count = 1;
			airTime = arrTimeDate.getTime()-depTimeDate.getTime();
		}

		return new Tuple2<RouteId, RouteInfo>(new RouteId(origin, dest, uniqueCarrier), new RouteInfo(flightDate, flightDate, distance, airTime, count, originCityName, destCityName));
	}
}
