package markersJob.functions;

import org.apache.spark.api.java.function.Function2;

public class ProduceMarkers implements Function2<String, String, String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String call(String arg0, String arg1) throws Exception {
		return arg0;
	}
}
