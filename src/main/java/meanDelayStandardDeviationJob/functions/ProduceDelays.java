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
		
		FlightInfoDelay fid = new FlightInfoDelay(arg0.getArrivalDelay()+arg1.getArrivalDelay()); 
		
		fid.setCountFlight(arg0.getCountFlight()+arg1.getCountFlight());
		fid.setDelay0(arg0.getDelay0()+arg1.getDelay0());
		fid.setDelay15(arg0.getDelay15()+arg1.getDelay15());
		fid.setDelay60(arg0.getDelay60()+arg1.getDelay60());
		fid.setDelay3h(arg0.getDelay3h()+arg1.getDelay3h());
		fid.setDelay24h(arg0.getDelay24h()+arg1.getDelay24h());
		fid.setDelayOther(arg0.getDelayOther()+arg1.getDelayOther());
		
		return fid;
	}

}
