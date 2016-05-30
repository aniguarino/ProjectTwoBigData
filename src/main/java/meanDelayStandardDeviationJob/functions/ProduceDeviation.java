package meanDelayStandardDeviationJob.functions;

import org.apache.spark.api.java.function.PairFunction;

import meanDelayStandardDeviationJob.model.FlightId;
import meanDelayStandardDeviationJob.model.FlightInfoDelay;
import scala.Tuple2;

public class ProduceDeviation
		implements PairFunction<Tuple2<FlightId, Tuple2<FlightInfoDelay, FlightInfoDelay>>, FlightId, FlightInfoDelay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Tuple2<FlightId, FlightInfoDelay> call(Tuple2<FlightId, Tuple2<FlightInfoDelay, FlightInfoDelay>> arg0)
			throws Exception {
		Double deviation = Math.pow(arg0._2._1.getArrivalDelay()-arg0._2._2.getMeanDelay(), 2);
		
		return new Tuple2<FlightId, FlightInfoDelay>(arg0._1, new FlightInfoDelay(arg0._2._1.getArrivalDelay(),
				deviation, arg0._2._2.getMeanDelay(), 1));
	}

}
