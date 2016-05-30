package ghostFlightJob.model;

import java.io.Serializable;

public class CompanyId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uniqueCarrier;
	private String year;
	private String month;
	
	public CompanyId(String uniqueCarrier, String year, String month) {
		super();
		this.uniqueCarrier = uniqueCarrier;
		this.year = year;
		this.month = month;
	}
	
	public String getUniqueCarrier() {
		return uniqueCarrier;
	}
	
	public void setUniqueCarrier(String uniqueCarrier) {
		this.uniqueCarrier = uniqueCarrier;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((uniqueCarrier == null) ? 0 : uniqueCarrier.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyId other = (CompanyId) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (uniqueCarrier == null) {
			if (other.uniqueCarrier != null)
				return false;
		} else if (!uniqueCarrier.equals(other.uniqueCarrier))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

}
