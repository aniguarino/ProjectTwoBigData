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
	private Integer countAirTime;
	private FlightInfoDelay infoDelay;
	private String originCity;
	private String destCity;
	
	public RouteInfo(String minFlightDate, String maxFlightDate, String distance, Long airTime, Integer countAirTime,
			FlightInfoDelay infoDelay, String originCity, String destCity) {
		super();
		this.minFlightDate = minFlightDate;
		this.maxFlightDate = maxFlightDate;
		this.distance = distance;
		this.airTime = airTime;
		this.countAirTime = countAirTime;
		this.infoDelay = infoDelay;
		this.originCity = originCity;
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

	public FlightInfoDelay getInfoDelay() {
		return infoDelay;
	}

	public void setInfoDelay(FlightInfoDelay infoDelay) {
		this.infoDelay = infoDelay;
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
}
