package meanDelayJob.model;

import java.io.Serializable;

public class FlightInfoDelay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double arrivalDelay; 
	private Integer countFlight;
	
	public FlightInfoDelay (Double meanDelay, Integer countFlights){
		this.arrivalDelay = meanDelay;
		this.countFlight = countFlights;
	}

	public Double getArrivalDelay() {
		return arrivalDelay;
	}

	public void setArrivalDelay(Double meanDelay) {
		this.arrivalDelay = meanDelay;
	}

	public Integer getCountFlight() {
		return countFlight;
	}

	public void setCountFlight(Integer countFlights) {
		this.countFlight = countFlights;
	}
	
}
