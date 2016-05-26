package routesJob.functions;

import java.util.concurrent.TimeUnit;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

import routesJob.model.RouteId;
import routesJob.model.RouteInfo;
import scala.Tuple2;

public class SaveMongoCalcMean implements PairFunction<Tuple2<RouteId, RouteInfo>, Object, BSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<Object, BSONObject> call(Tuple2<RouteId, RouteInfo> arg0) throws Exception {
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
				append("DistanceKm", (Double.parseDouble(arg0._2.getDistance())*1.60934)+"").
				append("OriginMarker", arg0._1.getOriginIata()+", "+arg0._2.getOriginCity()).
				append("DestMarker", arg0._1.getDestIata()+", "+arg0._2.getDestCity());
		
		return new Tuple2<Object, BSONObject>(null, save);
	}

}
