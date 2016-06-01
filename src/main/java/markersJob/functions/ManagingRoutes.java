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
		
		String iataOrigin = (String) arg._2.get("OriginIata");
		String labelCityOrigin = (String) arg._2.get("OriginCity");
		String latitudeOrigin = (String) arg._2.get("OriginLatitude");
		String longitudeOrigin = (String) arg._2.get("OriginLongitude");
		
		rtn.add(new MarkerInfo(iataOrigin, labelCityOrigin, latitudeOrigin, longitudeOrigin));
		
		String iataDest = (String) arg._2.get("DestIata");
		String labelCityDest = (String) arg._2.get("DestCity");
		String latitudeDest = (String) arg._2.get("DestLatitude");
		String longitudeDest = (String) arg._2.get("DestLongitude");
		rtn.add(new MarkerInfo(iataDest, labelCityDest, latitudeDest, longitudeDest));
		
		return rtn;
	}
}
