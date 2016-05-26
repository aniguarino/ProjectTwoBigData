package markersJob.functions;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.bson.BSONObject;

import scala.Tuple2;

public class ManagingRoutes implements PairFlatMapFunction<Tuple2<Object, BSONObject>, String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Iterable<Tuple2<String, String>> call(Tuple2<Object, BSONObject> arg) {
		List<Tuple2<String, String>> rtn = new ArrayList<Tuple2<String, String>>();
		
		String markerCodeOrigin = (String) arg._2.get("OriginMarker");
		String labelCityOrigin = (String) arg._2.get("OriginCity");
		rtn.add(new Tuple2<String, String>(markerCodeOrigin, labelCityOrigin));
		
		String markerCodeDest = (String) arg._2.get("DestMarker");
		String labelCityDest = (String) arg._2.get("DestCity");
		rtn.add(new Tuple2<String, String>(markerCodeDest, labelCityDest));
		
		return rtn;
	}
}
