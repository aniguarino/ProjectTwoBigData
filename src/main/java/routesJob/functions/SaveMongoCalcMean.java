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
		
		if(origin == null)
			origin = new AirportInfo(null, null, null, "0", "0");
		if(dest == null)
			dest = new AirportInfo(null, null, null, "0", "0");
		
		Long meanAirTime = 0L;
		
		if(arg0._2.getCountAirTime() != 0)
			meanAirTime = arg0._2.getAirTime()/arg0._2.getCountAirTime();
		
		String stringMeanAirTime = longToStringMinutes(meanAirTime * 60 * 1000);
		
		Long meanDepDelay = 0L;
		
		if(arg0._2.getCountAirTime() != 0)
			meanDepDelay = (long) (arg0._2.getInfoDelay().getDepDelay()/arg0._2.getInfoDelay().getCountDelay());
		
		String stringMeanDepDelay = longToStringMinutes(meanDepDelay * 60 * 1000);
		
		Long meanArrDelay = 0L;
		
		if(arg0._2.getCountAirTime() != 0)
			meanArrDelay = (long) (arg0._2.getInfoDelay().getArrDelay()/arg0._2.getInfoDelay().getCountDelay());
		
		String stringMeanArrDelay = longToStringMinutes(meanArrDelay * 60 * 1000);
		
		BSONObject save = new BasicDBObject().
				append("FlightDateMax", arg0._2.getMaxFlightDate().toString()).
				append("FlightDateMin", arg0._2.getMinFlightDate().toString()).
				append("UniqueCarrier", arg0._1.getUniqueCarrier()).
				append("AirTime", stringMeanAirTime).
				append("MeanDepDelay", stringMeanDepDelay).
				append("CountDelayDep0", arg0._2.getInfoDelay().getDelayDep0()).
				append("CountDelayDep15", arg0._2.getInfoDelay().getDelayDep15()).
				append("CountDelayDep60", arg0._2.getInfoDelay().getDelayDep60()).
				append("CountDelayDep3h", arg0._2.getInfoDelay().getDelayDep3h()).
				append("CountDelayDep24h", arg0._2.getInfoDelay().getDelayDep24h()).
				append("CountDelayDepOther", arg0._2.getInfoDelay().getDelayDepOther()).
				append("MeanArrDelay", stringMeanArrDelay).
				append("CountDelayArr0", arg0._2.getInfoDelay().getDelayArr0()).
				append("CountDelayArr15", arg0._2.getInfoDelay().getDelayArr15()).
				append("CountDelayArr60", arg0._2.getInfoDelay().getDelayArr60()).
				append("CountDelayArr3h", arg0._2.getInfoDelay().getDelayArr3h()).
				append("CountDelayArr24h", arg0._2.getInfoDelay().getDelayArr24h()).
				append("CountDelayArrOther", arg0._2.getInfoDelay().getDelayArrOther()).
				append("CarrierDelay", arg0._2.getInfoDelay().getCarrierDelay()).
				append("WeatherDelay", arg0._2.getInfoDelay().getWeatherDelay()).
				append("NASDelay", arg0._2.getInfoDelay().getNASDelay()).
				append("SecurityDelay", arg0._2.getInfoDelay().getSecurityDelay()).
				append("LateAircraftDelay", arg0._2.getInfoDelay().getLateAircraftDelay()).
				append("DistanceMiles", arg0._2.getDistance()).
				append("DistanceKm", (Double.parseDouble(arg0._2.getDistance())*unitMilesInKm)+"").
				append("OriginIata", arg0._1.getOriginIata()).
				append("OriginLatitude", origin.getLatitude()).
				append("OriginLongitude", origin.getLongitude()).
				append("OriginCity", arg0._2.getOriginCity()).
				append("DestIata", arg0._1.getDestIata()).
				append("DestLatitude", dest.getLatitude()).
				append("DestLongitude", dest.getLongitude()).
				append("DestCity", arg0._2.getDestCity());
		
		return new Tuple2<Object, BSONObject>(null, save);
	}
	
	private String longToStringMinutes(Long meanAirTime){
		String meanAirTimeHours = TimeUnit.MILLISECONDS.toHours(meanAirTime)+"";
		if(meanAirTimeHours.length() == 1)
			meanAirTimeHours = "0".concat(meanAirTimeHours);
		
		String meanAirTimeMin = TimeUnit.MILLISECONDS.toMinutes(meanAirTime)%60+"";
		if(meanAirTimeMin.length() == 1)
			meanAirTimeMin = "0".concat(meanAirTimeMin);
		
		return meanAirTimeHours+":"+meanAirTimeMin;
	}
}
