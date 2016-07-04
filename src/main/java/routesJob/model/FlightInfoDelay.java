package routesJob.model;

import java.io.Serializable;

public class FlightInfoDelay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double depDelay;
	private Integer delayDep0;
	private Integer delayDep15;
	private Integer delayDep60;
	private Integer delayDep3h;
	private Integer delayDep24h;
	private Integer delayDepOther;
	
	private Double arrDelay;
	private Integer delayArr0;
	private Integer delayArr15;
	private Integer delayArr60;
	private Integer delayArr3h;
	private Integer delayArr24h;
	private Integer delayArrOther;
	
	private Integer countDelay;
	
	private Double carrierDelay;
	private Double weatherDelay;
	private Double NASDelay;
	private Double securityDelay;
	private Double lateAircraftDelay;

	public FlightInfoDelay(Double depDelay, Integer delayDep0, Integer delayDep15,
			Integer delayDep60, Integer delayDep3h, Integer delayDep24h, Integer delayDepOther, Double arrDelay,
			Integer delayArr0, Integer delayArr15, Integer delayArr60, Integer delayArr3h,
			Integer delayArr24h, Integer delayArrOther, Integer countDelay) {
		this.depDelay = depDelay;
		this.delayDep0 = delayDep0;
		this.delayDep15 = delayDep15;
		this.delayDep60 = delayDep60;
		this.delayDep3h = delayDep3h;
		this.delayDep24h = delayDep24h;
		this.delayDepOther = delayDepOther;
		this.arrDelay = arrDelay;
		this.delayArr0 = delayArr0;
		this.delayArr15 = delayArr15;
		this.delayArr60 = delayArr60;
		this.delayArr3h = delayArr3h;
		this.delayArr24h = delayArr24h;
		this.delayArrOther = delayArrOther;
		this.countDelay = countDelay;
	}
	
	public FlightInfoDelay(Double arrDelay, Double depDelay, Double carrierDelay, Double weatherDelay, Double NASDelay,
			Double securityDelay, Double lateAircraftDelay) {
		this.arrDelay = arrDelay;
		
		this.delayArr0 = 0;
		this.delayArr15 = 0;
		this.delayArr60 = 0;
		this.delayArr3h = 0;
		this.delayArr24h = 0;
		this.delayArrOther = 0;
		
		if(arrDelay == 0)
			this.delayArr0 = 1;
		if(arrDelay>0 && arrDelay<=15)
			this.delayArr15 = 1;
		if(arrDelay>15 && arrDelay<=60)
			this.delayArr60 = 1;
		if(arrDelay>60 && arrDelay<=180)
			this.delayArr3h = 1;
		if(arrDelay>180 && arrDelay<=1440)
			this.delayArr24h = 1;
		if(arrDelay>1440)
			this.delayArrOther = 1;
		
		this.depDelay = depDelay;
		
		this.delayDep0 = 0;
		this.delayDep15 = 0;
		this.delayDep60 = 0;
		this.delayDep3h = 0;
		this.delayDep24h = 0;
		this.delayDepOther = 0;
		
		if(depDelay == 0)
			this.delayDep0 = 1;
		if(depDelay>0 && depDelay<=15)
			this.delayDep15 = 1;
		if(depDelay>15 && depDelay<=60)
			this.delayDep60 = 1;
		if(depDelay>60 && depDelay<=180)
			this.delayDep3h = 1;
		if(depDelay>180 && depDelay<=1440)
			this.delayDep24h = 1;
		if(depDelay>1440)
			this.delayDepOther = 1;
		
		this.countDelay = 1;
		
		this.carrierDelay = carrierDelay;
		this.weatherDelay = weatherDelay;
		this.NASDelay = NASDelay;
		this.securityDelay = securityDelay;
		this.lateAircraftDelay = lateAircraftDelay;
	}
	
	public FlightInfoDelay(){
		
	}

	public Double getArrDelay() {
		return arrDelay;
	}

	public void setArrDelay(Double arrDelay) {
		this.arrDelay = arrDelay;
	}

	public Integer getDelayDep0() {
		return delayDep0;
	}

	public void setDelayDep0(Integer delayDep0) {
		this.delayDep0 = delayDep0;
	}

	public Integer getDelayDep15() {
		return delayDep15;
	}

	public void setDelayDep15(Integer delayDep15) {
		this.delayDep15 = delayDep15;
	}

	public Integer getDelayDep60() {
		return delayDep60;
	}

	public void setDelayDep60(Integer delayDep60) {
		this.delayDep60 = delayDep60;
	}

	public Integer getDelayDep3h() {
		return delayDep3h;
	}

	public void setDelayDep3h(Integer delayDep3h) {
		this.delayDep3h = delayDep3h;
	}

	public Integer getDelayDep24h() {
		return delayDep24h;
	}

	public void setDelayDep24h(Integer delayDep24h) {
		this.delayDep24h = delayDep24h;
	}

	public Integer getDelayDepOther() {
		return delayDepOther;
	}

	public void setDelayDepOther(Integer delayDepOther) {
		this.delayDepOther = delayDepOther;
	}

	public Double getDepDelay() {
		return depDelay;
	}

	public void setDepDelay(Double depDelay) {
		this.depDelay = depDelay;
	}

	public Integer getDelayArr0() {
		return delayArr0;
	}

	public void setDelayArr0(Integer delayArr0) {
		this.delayArr0 = delayArr0;
	}

	public Integer getDelayArr15() {
		return delayArr15;
	}

	public void setDelayArr15(Integer delayArr15) {
		this.delayArr15 = delayArr15;
	}

	public Integer getDelayArr60() {
		return delayArr60;
	}

	public void setDelayArr60(Integer delayArr60) {
		this.delayArr60 = delayArr60;
	}

	public Integer getDelayArr3h() {
		return delayArr3h;
	}

	public void setDelayArr3h(Integer delayArr3h) {
		this.delayArr3h = delayArr3h;
	}

	public Integer getDelayArr24h() {
		return delayArr24h;
	}

	public void setDelayArr24h(Integer delayArr24h) {
		this.delayArr24h = delayArr24h;
	}

	public Integer getDelayArrOther() {
		return delayArrOther;
	}

	public void setDelayArrOther(Integer delayArrOther) {
		this.delayArrOther = delayArrOther;
	}

	public Integer getCountDelay() {
		return countDelay;
	}

	public void setCountDelay(Integer countDelay) {
		this.countDelay = countDelay;
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

	public FlightInfoDelay sum(FlightInfoDelay infoDelay) {
		FlightInfoDelay rtn = new FlightInfoDelay();
		
		rtn.setDepDelay(this.depDelay + infoDelay.getDepDelay());
		rtn.setDelayDep0(this.delayDep0 + infoDelay.getDelayDep0());
		rtn.setDelayDep15(this.delayDep15 + infoDelay.getDelayDep15());
		rtn.setDelayDep60(this.delayDep60 + infoDelay.getDelayDep60());
		rtn.setDelayDep3h(this.delayDep3h + infoDelay.getDelayDep3h());
		rtn.setDelayDep24h(this.delayDep24h + infoDelay.getDelayDep24h());
		rtn.setDelayDepOther(this.delayDepOther + infoDelay.getDelayDepOther());
		
		rtn.setArrDelay(this.arrDelay + infoDelay.getArrDelay());
		rtn.setDelayArr0(this.delayArr0 + infoDelay.getDelayArr0());
		rtn.setDelayArr15(this.delayArr15 + infoDelay.getDelayArr15());
		rtn.setDelayArr60(this.delayArr60 + infoDelay.getDelayArr60());
		rtn.setDelayArr3h(this.delayArr3h + infoDelay.getDelayArr3h());
		rtn.setDelayArr24h(this.delayArr24h + infoDelay.getDelayArr24h());
		rtn.setDelayArrOther(this.delayArrOther + infoDelay.getDelayArrOther());
		
		rtn.setCountDelay(this.countDelay + infoDelay.getCountDelay());
		
		rtn.setCarrierDelay(this.carrierDelay + infoDelay.getCarrierDelay());
		rtn.setWeatherDelay(this.weatherDelay + infoDelay.getWeatherDelay());
		rtn.setNASDelay(this.NASDelay + infoDelay.getNASDelay());
		rtn.setSecurityDelay(this.securityDelay + infoDelay.getSecurityDelay());
		rtn.setLateAircraftDelay(this.lateAircraftDelay + infoDelay.getLateAircraftDelay());
		
		return rtn;
	}
}
