package markersJob.model;

import java.io.Serializable;

public class MarkerDelay implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long meanDelayDep;
	private Integer countDep;
	private Long meanDelayArr;
	private Integer countArr;

	private Integer delayDep0;
	private Integer delayDep15;
	private Integer delayDep60;
	private Integer delayDep3h;
	private Integer delayDep24h;
	private Integer delayDepOther;
	
	private Integer delayArr0;
	private Integer delayArr15;
	private Integer delayArr60;
	private Integer delayArr3h;
	private Integer delayArr24h;
	private Integer delayArrOther;
	
	public MarkerDelay(Long meanDelayDep, Integer countDep, Long meanDelayArr, Integer countArr, Integer delayDep0,
			Integer delayDep15, Integer delayDep60, Integer delayDep3h, Integer delayDep24h, Integer delayDepOther,
			Integer delayArr0, Integer delayArr15, Integer delayArr60, Integer delayArr3h, Integer delayArr24h,
			Integer delayArrOther) {
		this.meanDelayDep = meanDelayDep;
		this.countDep = countDep;
		this.meanDelayArr = meanDelayArr;
		this.countArr = countArr;
		this.delayDep0 = delayDep0;
		this.delayDep15 = delayDep15;
		this.delayDep60 = delayDep60;
		this.delayDep3h = delayDep3h;
		this.delayDep24h = delayDep24h;
		this.delayDepOther = delayDepOther;
		this.delayArr0 = delayArr0;
		this.delayArr15 = delayArr15;
		this.delayArr60 = delayArr60;
		this.delayArr3h = delayArr3h;
		this.delayArr24h = delayArr24h;
		this.delayArrOther = delayArrOther;
	}

	public Long getMeanDelayDep() {
		return meanDelayDep;
	}

	public void setMeanDelayDep(Long meanDelayDep) {
		this.meanDelayDep = meanDelayDep;
	}

	public Integer getCountDep() {
		return countDep;
	}

	public void setCountDep(Integer countDep) {
		this.countDep = countDep;
	}

	public Long getMeanDelayArr() {
		return meanDelayArr;
	}

	public void setMeanDelayArr(Long meanDelayArr) {
		this.meanDelayArr = meanDelayArr;
	}

	public Integer getCountArr() {
		return countArr;
	}

	public void setCountArr(Integer countArr) {
		this.countArr = countArr;
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

	public MarkerDelay sum(MarkerDelay o){
		Long meanDelayDep = this.meanDelayDep + o.getMeanDelayDep();
		Long meanDelayArr = this.meanDelayArr + o.getMeanDelayArr();
		Integer countDep = this.countDep + o.getCountDep();
		Integer countArr = this.countArr + o.getCountArr();
		Integer delayDep0 = this.delayDep0 + o.getDelayDep0();
		Integer delayDep15 = this.delayDep15 + o.getDelayDep15();
		Integer delayDep60 = this.delayDep60 + o.getDelayDep60();
		Integer delayDep3h = this.delayDep3h + o.getDelayDep3h();
		Integer delayDep24h = this.delayDep24h + o.getDelayDep24h();
		Integer delayDepOther = this.delayDepOther + o.getDelayDepOther();
		Integer delayArr0 = this.delayArr0 + o.getDelayArr0();
		Integer delayArr15 = this.delayArr15 + o.getDelayArr15();
		Integer delayArr60 = this.delayArr60 + o.getDelayArr60();
		Integer delayArr3h = this.delayArr3h + o.getDelayArr3h();
		Integer delayArr24h = this.delayArr24h + o.getDelayArr24h();
		Integer delayArrOther = this.delayArrOther + o.getDelayArrOther();
		
		return new MarkerDelay(meanDelayDep, countDep, meanDelayArr, countArr, delayDep0, delayDep15, delayDep60, delayDep3h, delayDep24h,
				delayDepOther, delayArr0, delayArr15, delayArr60, delayArr3h, delayArr24h, delayArrOther);
	}
}
