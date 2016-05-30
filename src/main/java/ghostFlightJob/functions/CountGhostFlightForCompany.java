package ghostFlightJob.functions;

import org.apache.spark.api.java.function.Function2;

import ghostFlightJob.model.Counters;

public class CountGhostFlightForCompany implements Function2<Counters, Counters, Counters> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Counters call(Counters v1, Counters v2) throws Exception {
		return new Counters(v1.getCounterGhost()+v2.getCounterGhost(),
				v1.getCounterAll()+v2.getCounterAll(),
				"");
	}

}
