package markersJob.functions;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.bson.BSONObject;

import markersJob.model.MarkerInfo;
import scala.Tuple2;

public class ManagingRoutes implements FlatMapFunction<Tuple2<Object, BSONObject>, MarkerInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Iterable<MarkerInfo> call(Tuple2<Object, BSONObject> arg) {
		List<MarkerInfo> rtn = new ArrayList<MarkerInfo>();
		
		String markerCodeOrigin = (String) arg._2.get("OriginMarker");
		String labelCityOrigin = (String) arg._2.get("OriginCity");
		String latitudeOrigin = (String) arg._2.get("OriginLatitude");
		String longitudeOrigin = (String) arg._2.get("OriginLongitude");
		
		rtn.add(new MarkerInfo(markerCodeOrigin, labelCityOrigin, latitudeOrigin, longitudeOrigin));
		
		String markerCodeDest = (String) arg._2.get("DestMarker");
		String labelCityDest = (String) arg._2.get("DestCity");
		String latitudeDest = (String) arg._2.get("DestLatitude");
		String longitudeDest = (String) arg._2.get("DestLongitude");
		rtn.add(new MarkerInfo(markerCodeDest, labelCityDest, latitudeDest, longitudeDest));
		
		return rtn;
	}
}
