package routesJob.functions;

import java.text.ParseException;

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

		return new Tuple2<RouteId, RouteInfo>(new RouteId(origin, dest), new RouteInfo(flightDate,
				originCityName, null, null, destCityName, null, null));
	}
}
