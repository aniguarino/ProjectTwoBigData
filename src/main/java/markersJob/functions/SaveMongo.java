package markersJob.functions;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import scala.Tuple2;

public class SaveMongo implements PairFunction<Tuple2<String, String>, Object, BSONObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<Object, BSONObject> call(Tuple2<String, String> arg0) throws Exception {		
		BSONObject save = new BasicDBObject().
				append("MarkerCode", arg0._1).
				append("LabelCity", arg0._2);
		
		return new Tuple2<Object, BSONObject>(null, save);
	}

}
