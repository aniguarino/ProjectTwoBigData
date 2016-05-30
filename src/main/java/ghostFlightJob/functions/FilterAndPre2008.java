package ghostFlightJob.functions;

import org.apache.spark.api.java.function.Function;
import org.bson.BSONObject;

import scala.Tuple2;

public class FilterAndPre2008 implements Function<Tuple2<Object, BSONObject>, Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Boolean call(Tuple2<Object, BSONObject> v1) throws Exception {
		if((Double)v1._2.get("Cancelled") == 1 ||
				(!v1._2.get("Div2Airport").equals("") && (Double)v1._2.get("DivReachedDest") == 1.0) ||
				!v1._2.get("Div3Airport").equals("") || !v1._2.get("Div4Airport").equals("") ||
				!v1._2.get("Div5Airport").equals(""))
			return false;
		
		if((Integer)v1._2.get("Year")<2008 || 
				((Integer)v1._2.get("Year") == 2008 && (Integer)v1._2.get("Month")<10))
			return false;
		return true;
	}

}
