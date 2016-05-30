package meanDelayStandardDeviationJob.functions;

import org.apache.spark.api.java.function.PairFunction;
import org.bson.BSONObject;

import meanDelayStandardDeviationJob.model.FlightId;
import meanDelayStandardDeviationJob.model.FlightInfoDelay;
import scala.Tuple2;

public class ManagingRoutes implements PairFunction<Tuple2<Object, BSONObject>, FlightId, FlightInfoDelay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<FlightId, FlightInfoDelay> call(Tuple2<Object, BSONObject> arg) throws Exception {
		String uniqueCarrier = (String) arg._2.get("UniqueCarrier");
		String year = arg._2.get("Year").toString();
		String month = arg._2.get("Month").toString();
		String dayOfWeek = arg._2.get("DayOfWeek").toString();
		Double arrivalDelay = (Double) arg._2.get("ArrDelayMinutes");
		
		return new Tuple2<FlightId, FlightInfoDelay>(new FlightId(uniqueCarrier, year, month, dayOfWeek),
					new FlightInfoDelay(arrivalDelay, 0.0, 0.0, 1));
	}

}
