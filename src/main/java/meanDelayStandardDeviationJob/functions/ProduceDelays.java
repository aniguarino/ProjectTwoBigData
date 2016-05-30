package meanDelayStandardDeviationJob.functions;

import org.apache.spark.api.java.function.Function2;

import meanDelayStandardDeviationJob.model.FlightInfoDelay;

public class ProduceDelays implements Function2<FlightInfoDelay, FlightInfoDelay, FlightInfoDelay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FlightInfoDelay call(FlightInfoDelay arg0, FlightInfoDelay arg1) throws Exception {
		
		FlightInfoDelay fid = new FlightInfoDelay(
				arg0.getArrivalDelay()+arg1.getArrivalDelay(),
				0.0,
				0.0,
				arg0.getCountFlight()+arg1.getCountFlight()
				); 
		
		return fid;
	}

}
