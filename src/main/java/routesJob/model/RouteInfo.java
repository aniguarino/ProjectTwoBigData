package routesJob.model;

import java.io.Serializable;

public class RouteInfo implements Comparable<RouteInfo>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String flightDate;
	private String distance;
	private String originCity;
	private String destCity;


	public RouteInfo(String flightDate, String distance, String originCity, String destCity){
		this.flightDate = flightDate;
		this.distance = distance;
		this.originCity = originCity;
		this.destCity = destCity;
	}
	
	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	@Override
	public int compareTo(RouteInfo o) {
		return this.flightDate.compareTo(o.getFlightDate());
	}
	
}
