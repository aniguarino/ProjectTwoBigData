package routesDistinctJob.functions;

import org.apache.spark.api.java.function.Function;

import routesDistinctJob.model.AirportInfo;
import scala.Tuple2;

public class FilterAirport implements Function<Tuple2<String, AirportInfo>, Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Boolean call(Tuple2<String, AirportInfo> value) throws Exception {
		if(value._2.getIataCode() != null)
			return true;
		return false;
	}
}