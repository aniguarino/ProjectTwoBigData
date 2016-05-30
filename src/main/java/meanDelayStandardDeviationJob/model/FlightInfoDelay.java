package meanDelayStandardDeviationJob.model;

import java.io.Serializable;

public class FlightInfoDelay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double arrivalDelay;
	private Double deviation;
	private Double meanDelay;
	private Integer countFlight;
	
	public FlightInfoDelay (Double arrivalDelay, Double deviation, Double meanDelay, Integer countFlights){
		this.arrivalDelay = arrivalDelay;
		this.deviation = deviation;
		this.meanDelay = meanDelay;
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

	public Double getDeviation() {
		return deviation;
	}

	public void setDeviation(Double deviation) {
		this.deviation = deviation;
	}

	public Double getMeanDelay() {
		return meanDelay;
	}

	public void setMeanDelay(Double meanDelay) {
		this.meanDelay = meanDelay;
	}
	
}
