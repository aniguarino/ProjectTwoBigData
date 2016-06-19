package markersJob.functions;

import org.apache.spark.api.java.function.Function2;

import markersJob.model.MarkerDelay;

public class ProduceMarkers implements Function2<MarkerDelay, MarkerDelay, MarkerDelay> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public MarkerDelay call(MarkerDelay arg0, MarkerDelay arg1) throws Exception {
		return arg0.sum(arg1);
	}
}
