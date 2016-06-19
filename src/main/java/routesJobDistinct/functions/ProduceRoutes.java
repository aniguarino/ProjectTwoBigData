package routesJobDistinct.functions;

import org.apache.spark.api.java.function.Function2;

import routesJobDistinct.model.RouteInfo;

public class ProduceRoutes implements Function2<RouteInfo, RouteInfo, RouteInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public RouteInfo call(RouteInfo arg0, RouteInfo arg1) throws Exception {
		String minFlightDate = "";
		String maxFlightDate = "";
		
		if(arg0.getMinFlightDate().compareTo(arg1.getMinFlightDate())<0)
			minFlightDate = arg0.getMinFlightDate();
		else 
			minFlightDate = arg1.getMinFlightDate();
		
		if(arg0.getMaxFlightDate().compareTo(arg1.getMaxFlightDate())>0)
			maxFlightDate = arg0.getMaxFlightDate();
		else
			maxFlightDate = arg1.getMaxFlightDate();
		
		RouteInfo rtn = new RouteInfo(minFlightDate,
				maxFlightDate,
				arg0.getDistance(),
				arg0.getAirTime()+arg1.getAirTime(),
				arg0.getCountAirTime()+arg1.getCountAirTime(),
				arg0.getDepDelay()+arg1.getDepDelay(),
				arg0.getArrDelay()+arg1.getArrDelay(),
				arg0.getCountDelay()+arg1.getCountDelay(),
				arg0.getOriginCity(),
				arg0.getDestCity());

		return rtn;
	}
}
