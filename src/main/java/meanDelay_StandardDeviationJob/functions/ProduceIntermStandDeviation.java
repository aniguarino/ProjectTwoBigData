package meanDelay_StandardDeviationJob.functions;

import org.apache.spark.api.java.function.Function2;

import meanDelay_StandardDeviationJob.model.FlightInfoDelay;

public class ProduceIntermStandDeviation implements Function2<FlightInfoDelay, FlightInfoDelay, FlightInfoDelay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FlightInfoDelay call(FlightInfoDelay arg0, FlightInfoDelay arg1) throws Exception {
		FlightInfoDelay fid = new FlightInfoDelay(
				arg0.getArrivalDelay(),
				arg0.getIntermediateStandDeviation()+arg1.getIntermediateStandDeviation(),
				arg0.getCountFlight()
				); 
		
		return fid;
	}

}
