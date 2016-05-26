package routesJob.model;

import java.io.Serializable;

public class RouteInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String minFlightDate;
	private String maxFlightDate;
	private String distance;
	private Long airTime;
	private String originCity;
	private String destCity;
	private Integer countAirTime;


	public RouteInfo(String minFlightDate, String maxFlightDate, String distance, Long airTime, Integer countAirTime, String originCity, String destCity){
		this.minFlightDate = minFlightDate;
		this.maxFlightDate = maxFlightDate;
		this.distance = distance;
		this.airTime = airTime;
		this.countAirTime = countAirTime;
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

	public String getMinFlightDate() {
		return minFlightDate;
	}

	public void setMinFlightDate(String minFlightDate) {
		this.minFlightDate = minFlightDate;
	}

	public String getMaxFlightDate() {
		return maxFlightDate;
	}

	public void setMaxFlightDate(String maxFlightDate) {
		this.maxFlightDate = maxFlightDate;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Long getAirTime() {
		return airTime;
	}

	public void setAirTime(Long airTime) {
		this.airTime = airTime;
	}

	public Integer getCountAirTime() {
		return countAirTime;
	}

	public void setCountAirTime(Integer countAirTime) {
		this.countAirTime = countAirTime;
	}
}
