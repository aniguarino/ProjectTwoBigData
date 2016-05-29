package meanDelayJob.functions;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import meanDelayJob.model.FlightId;
import meanDelayJob.model.FlightInfoDelay;
import scala.Tuple2;

public class ManagingRoutes implements PairFunction<Tuple2<Object, BSONObject>, FlightId, FlightInfoDelay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<FlightId, FlightInfoDelay> call(Tuple2<Object, BSONObject> arg) throws Exception {
		String uniqueCarrier = (String) arg._2.get("UniqueCarrier");
		String year = (String) arg._2.get("Year").toString();
		String month = (String) arg._2.get("Month").toString();
		String day = (String) arg._2.get("DayofMonth").toString();
		Double arrivalDelay = Double.parseDouble(arg._2.get("ArrDelayMinutes").toString());
		
		return new Tuple2<FlightId, FlightInfoDelay>(new FlightId(uniqueCarrier, year, month, day),
					new FlightInfoDelay(arrivalDelay, 1));
	}

}
