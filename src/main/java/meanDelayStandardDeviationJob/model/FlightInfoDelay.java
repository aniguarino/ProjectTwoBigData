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
	
	private Integer delay0;
	private Integer delay15;
	private Integer delay60;
	private Integer delay3h;
	private Integer delay24h;
	private Integer delayOther;
	
	public FlightInfoDelay (Double arrivalDelay){
		
		this.arrivalDelay = arrivalDelay;
		this.countFlight = 1;
		
		this.delay0 = 0;
		this.delay15 = 0;
		this.delay60 = 0;
		this.delay3h = 0;
		this.delay24h = 0;
		this.delayOther = 0;
		
		if(arrivalDelay == 0)
			this.delay0 = 1;
		if(arrivalDelay>0 && arrivalDelay<=15)
			this.delay15 = 1;
		if(arrivalDelay>15 && arrivalDelay<=60)
			this.delay60 = 1;
		if(arrivalDelay>60 && arrivalDelay<=180)
			this.delay3h = 1;
		if(arrivalDelay>180 && arrivalDelay<=1440)
			this.delay24h = 1;
		if(arrivalDelay>1440)
			this.delayOther = 1;
	}
	
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

	public Integer getDelay0() {
		return delay0;
	}

	public void setDelay0(Integer delay0) {
		this.delay0 = delay0;
	}

	public Integer getDelay15() {
		return delay15;
	}

	public void setDelay15(Integer delay15) {
		this.delay15 = delay15;
	}

	public Integer getDelay60() {
		return delay60;
	}

	public void setDelay60(Integer delay60) {
		this.delay60 = delay60;
	}

	public Integer getDelay3h() {
		return delay3h;
	}

	public void setDelay3h(Integer delay3h) {
		this.delay3h = delay3h;
	}

	public Integer getDelay24h() {
		return delay24h;
	}

	public void setDelay24h(Integer delay24h) {
		this.delay24h = delay24h;
	}

	public Integer getDelayOther() {
		return delayOther;
	}

	public void setDelayOther(Integer delayOther) {
		this.delayOther = delayOther;
	}
	
}
