package routesJob.functions;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

import routesJob.model.AirportInfo;
import routesJob.model.RouteId;
import routesJob.model.RouteInfo;
import scala.Tuple2;

public class SaveMongoCalcMean implements PairFunction<Tuple2<RouteId, RouteInfo>, Object, BSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Double unitMilesInKm = 1.60934;
	
	private Broadcast<Map<String, AirportInfo>> airportsBroadcast;

	public SaveMongoCalcMean(Broadcast<Map<String, AirportInfo>> airportsBroadcast) {
		this.airportsBroadcast = airportsBroadcast;
	}

	@Override
	public Tuple2<Object, BSONObject> call(Tuple2<RouteId, RouteInfo> arg0) throws Exception {
		AirportInfo origin = airportsBroadcast.getValue().get(arg0._1.getOriginIata());
		AirportInfo dest = airportsBroadcast.getValue().get(arg0._1.getDestIata());
		
		Long meanAirTime = 0L;
		
		if(arg0._2.getCountAirTime() != 0L)
			meanAirTime = arg0._2.getAirTime()/arg0._2.getCountAirTime();
		
		String meanAirTimeHours = TimeUnit.MILLISECONDS.toHours(meanAirTime)+"";
		if(meanAirTimeHours.length() == 1)
			meanAirTimeHours = "0".concat(meanAirTimeHours);
		
		String meanAirTimeMin = TimeUnit.MILLISECONDS.toMinutes(meanAirTime)%60+"";
		if(meanAirTimeMin.length() == 1)
			meanAirTimeMin = "0".concat(meanAirTimeMin);
		
		BSONObject save = new BasicDBObject().
				append("FlightDateMax", arg0._2.getMaxFlightDate().toString()).
				append("FlightDateMin", arg0._2.getMinFlightDate().toString()).
				append("UniqueCarrier", arg0._1.getUniqueCarrier()).
				append("AirTime", meanAirTimeHours+":"+meanAirTimeMin).
				append("DistanceMiles", arg0._2.getDistance()).
				append("DistanceKm", (Double.parseDouble(arg0._2.getDistance())*unitMilesInKm)+"").
				append("OriginIata", arg0._1.getOriginIata()).
				append("OriginMarker", arg0._1.getOriginIata()+", "+arg0._2.getOriginCity()).
				append("OriginLatitude", origin.getLatitude()).
				append("OriginLongitude", origin.getLongitude()).
				append("OriginCity", arg0._2.getOriginCity()).
				append("DestIata", arg0._1.getDestIata()).
				append("DestMarker", arg0._1.getDestIata()+", "+arg0._2.getDestCity()).
				append("DestLatitude", dest.getLatitude()).
				append("DestLongitude", dest.getLongitude()).
				append("DestCity", arg0._2.getDestCity());
		
		return new Tuple2<Object, BSONObject>(null, save);
	}

}
