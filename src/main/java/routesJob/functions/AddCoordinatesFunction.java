package routesJob.functions;

import java.util.Map;

import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;

import routesJob.model.AirportInfo;
import routesJob.model.RouteId;
import routesJob.model.RouteInfo;
import scala.Tuple2;

public class AddCoordinatesFunction implements PairFunction<Tuple2<RouteId, RouteInfo>, RouteId, RouteInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Broadcast<Map<String, AirportInfo>> airportsBroadcast;

	public AddCoordinatesFunction(Broadcast<Map<String, AirportInfo>> airportsBroadcast) {
		this.airportsBroadcast = airportsBroadcast;
	}

	@Override
	public Tuple2<RouteId, RouteInfo> call(Tuple2<RouteId, RouteInfo> arg0) throws Exception {
		AirportInfo origin = airportsBroadcast.getValue().get(arg0._1.getOriginIata());
		AirportInfo dest = airportsBroadcast.getValue().get(arg0._1.getDestIata());
		
		if(origin == null){
			throw new RuntimeException("Null origin iataCode: "+arg0._1.getOriginIata());
		}
		if(dest == null){
			throw new RuntimeException("Null dest iataCode: "+arg0._1.getDestIata());
		}
		
		String originLatitude = origin.getLatitude();
		String originLongitude = origin.getLongitude();
		String destLatitude = dest.getLatitude();
		String destLongitude = dest.getLongitude();
		
		arg0._2.setOriginLatitude(originLatitude);
		arg0._2.setOriginLongitude(originLongitude);
		arg0._2.setDestLatitude(destLatitude);
		arg0._2.setDestLongitude(destLongitude);
		
		return arg0;
	}
}
