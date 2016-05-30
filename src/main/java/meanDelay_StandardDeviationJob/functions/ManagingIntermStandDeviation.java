package meanDelay_StandardDeviationJob.functions;

import org.apache.spark.api.java.function.PairFunction;

import meanDelay_StandardDeviationJob.model.FlightId;
import meanDelay_StandardDeviationJob.model.FlightInfoDelay;
import scala.Tuple2;

public class ManagingIntermStandDeviation implements PairFunction<Tuple2<FlightId, FlightInfoDelay>, FlightId, FlightInfoDelay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<FlightId, FlightInfoDelay> call(Tuple2<FlightId, FlightInfoDelay> arg0) throws Exception {
		
		Double meanDelay = (arg0._2.getArrivalDelay()/arg0._2.getCountFlight());
		
		Double intermediateStandardDeviation = Math.pow(arg0._2.getArrivalDelay()-meanDelay, 2);
		
		return new Tuple2<FlightId, FlightInfoDelay>(new FlightId(arg0._1.getUniqueCarrier(), arg0._1.getYear(), 
				arg0._1.getMonth(), arg0._1.getDayOfWeek()), new FlightInfoDelay(arg0._2.getArrivalDelay(), 
				intermediateStandardDeviation, arg0._2.getCountFlight()));
	}

}
