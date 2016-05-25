package routesJob.functions;

import org.apache.spark.api.java.function.Function2;

import routesJob.model.RouteInfo;

public class ProduceRoutes implements Function2<RouteInfo, RouteInfo, RouteInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public RouteInfo call(RouteInfo arg0, RouteInfo arg1) throws Exception {
		if(arg0.getFlightDate().compareTo(arg1.getFlightDate())>0)
			return arg0;
		return arg1;
	}

}
