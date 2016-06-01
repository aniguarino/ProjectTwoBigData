package markersJob.functions;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

import markersJob.model.MarkerInfo;
import scala.Tuple2;

public class SaveMongo implements PairFunction<MarkerInfo, Object, BSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<Object, BSONObject> call(MarkerInfo arg0) throws Exception {
		BSONObject save = new BasicDBObject().
				append("Code", arg0.getMarkerCodeOrigin()).
				append("Latitude", arg0.getLatitude()).
				append("Longitude", arg0.getLongitude()).
				append("LabelCity", arg0.getLabel());
		
		return new Tuple2<Object, BSONObject>(null, save);
	}
}