package meanDelayStandardDeviationJob.functions;

import org.apache.spark.api.java.function.Function;
import org.bson.BSONObject;

import scala.Tuple2;

public class FilterCancelledAndDiverted implements Function<Tuple2<Object, BSONObject>, Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Boolean call(Tuple2<Object, BSONObject> v1) throws Exception {
		if((Double)v1._2.get("Cancelled") == 1 ||
				((Integer)v1._2.get("Year") < 2008 && (Integer)v1._2.get("Month") < 10))
			return false;
		return true;
	}
}
