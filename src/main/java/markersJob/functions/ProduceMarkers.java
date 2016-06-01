package markersJob.functions;

import org.apache.spark.api.java.function.Function2;

import markersJob.model.MarkerInfo;

public class ProduceMarkers implements Function2<MarkerInfo, MarkerInfo, MarkerInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public MarkerInfo call(MarkerInfo arg0, MarkerInfo arg1) throws Exception {
		return arg0;
	}
}
