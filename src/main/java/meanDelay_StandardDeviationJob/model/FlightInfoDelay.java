package meanDelay_StandardDeviationJob.model;

import java.io.Serializable;

public class FlightInfoDelay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double arrivalDelay;
	private Double intermediateStandDeviation;
	private Integer countFlight;
	
	public FlightInfoDelay (Double meanDelay, Double isd, Integer countFlights){
		this.arrivalDelay = meanDelay;
		this.intermediateStandDeviation = isd;
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

	public Double getIntermediateStandDeviation() {
		return intermediateStandDeviation;
	}

	public void setIntermediateStandDeviation(Double intermediateStandDeviation) {
		this.intermediateStandDeviation = intermediateStandDeviation;
	}
	
}
