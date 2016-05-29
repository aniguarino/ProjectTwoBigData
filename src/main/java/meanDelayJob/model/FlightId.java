package meanDelayJob.model;

import java.io.Serializable;

public class FlightId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uniqueCarrier;
	private String year;
	private String month;
	private String dayOfMonth;
	
	public FlightId (String uniqueCarrier, String year, String month, String dayOfMonth){
		this.uniqueCarrier = uniqueCarrier;
		this.year = year;
		this.month = month;
		this.dayOfMonth = dayOfMonth;
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

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayOfMonth == null) ? 0 : dayOfMonth.hashCode());
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
		FlightId other = (FlightId) obj;
		if (dayOfMonth == null) {
			if (other.dayOfMonth != null)
				return false;
		} else if (!dayOfMonth.equals(other.dayOfMonth))
			return false;
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
