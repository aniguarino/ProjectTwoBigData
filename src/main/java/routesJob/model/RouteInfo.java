package routesJob.model;

import java.io.Serializable;

public class RouteInfo implements Comparable<RouteInfo>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String flightDate;
	
	private String originCity;
	private String originLatitude;
	private String originLongitude;

	private String destCity;
	private String destLatitude;
	private String destLongitude;

	public RouteInfo(String flightDate, String originCity, String originLatitude, String originLongitude, String destCity, String destLatitude, String destLongitude){
		this.flightDate = flightDate;
		
		this.originCity = originCity;
		this.originLatitude = originLatitude;
		this.originLongitude = originLongitude;

		this.destCity = destCity;
		this.destLatitude = destLatitude;
		this.destLongitude = destLongitude;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginLatitude() {
		return originLatitude;
	}

	public void setOriginLatitude(String originLatitude) {
		this.originLatitude = originLatitude;
	}

	public String getOriginLongitude() {
		return originLongitude;
	}

	public void setOriginLongitude(String originLongitude) {
		this.originLongitude = originLongitude;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public String getDestLatitude() {
		return destLatitude;
	}

	public void setDestLatitude(String destLatitude) {
		this.destLatitude = destLatitude;
	}

	public String getDestLongitude() {
		return destLongitude;
	}

	public void setDestLongitude(String destLongitude) {
		this.destLongitude = destLongitude;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	@Override
	public int compareTo(RouteInfo o) {
		return this.flightDate.compareTo(o.getFlightDate());
	}
}
