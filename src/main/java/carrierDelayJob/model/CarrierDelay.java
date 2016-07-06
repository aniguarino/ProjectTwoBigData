package carrierDelayJob.model;

import java.io.Serializable;

public class CarrierDelay implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double carrierDelay;
	private Double weatherDelay;
	private Double NASDelay;
	private Double securityDelay;
	private Double lateAircraftDelay;
	
	public CarrierDelay(Double carrierDelay, Double weatherDelay, Double nASDelay, Double securityDelay,
			Double lateAircraftDelay) {
		super();
		this.carrierDelay = carrierDelay;
		this.weatherDelay = weatherDelay;
		NASDelay = nASDelay;
		this.securityDelay = securityDelay;
		this.lateAircraftDelay = lateAircraftDelay;
	}

	public Double getCarrierDelay() {
		return carrierDelay;
	}

	public void setCarrierDelay(Double carrierDelay) {
		this.carrierDelay = carrierDelay;
	}

	public Double getWeatherDelay() {
		return weatherDelay;
	}

	public void setWeatherDelay(Double weatherDelay) {
		this.weatherDelay = weatherDelay;
	}

	public Double getNASDelay() {
		return NASDelay;
	}

	public void setNASDelay(Double nASDelay) {
		NASDelay = nASDelay;
	}

	public Double getSecurityDelay() {
		return securityDelay;
	}

	public void setSecurityDelay(Double securityDelay) {
		this.securityDelay = securityDelay;
	}

	public Double getLateAircraftDelay() {
		return lateAircraftDelay;
	}
	
	public void setLateAircraftDelay(Double lateAircraftDelay) {
		this.lateAircraftDelay = lateAircraftDelay;
	}

	public CarrierDelay sum(CarrierDelay arg1) {
		Double carrierDelay = this.carrierDelay + arg1.getCarrierDelay();
		Double weatherDelay = this.weatherDelay + arg1.getWeatherDelay();
		Double NASDelay = this.NASDelay + arg1.getNASDelay();
		Double securityDelay = this.securityDelay + arg1.getSecurityDelay();
		Double lateAircraftDelay = this.lateAircraftDelay + arg1.getLateAircraftDelay();
		
		return new CarrierDelay(carrierDelay, weatherDelay, NASDelay, securityDelay, lateAircraftDelay);
	}
}
