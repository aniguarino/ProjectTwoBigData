package routesJob.functions;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

import routesJob.model.RouteId;
import routesJob.model.RouteInfo;
import scala.Tuple2;

public class SaveMongo implements PairFunction<Tuple2<RouteId, RouteInfo>, Object, BSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<Object, BSONObject> call(Tuple2<RouteId, RouteInfo> arg0) throws Exception {
		BSONObject save = new BasicDBObject().
				append("FlightDateMax", arg0._2.getFlightDate().toString()).
				append("UniqueCarrier", arg0._1.getUniqueCarrier()).
				//append("AirTime", arg0._2.getAirTime()).
				append("Distance", arg0._2.getDistance()).
				append("OriginMarker", arg0._1.getOriginIata()+", "+arg0._2.getOriginCity()).
				append("Origin", arg0._1.getOriginIata()).
				append("OriginCity", arg0._2.getOriginCity()).
				append("DestMarker", arg0._1.getDestIata()+", "+arg0._2.getDestCity()).
				append("Dest", arg0._1.getDestIata()).
				append("DestCity", arg0._2.getDestCity());
		return new Tuple2<Object, BSONObject>(null, save);
	}

}
