package routesJob.model;

import java.io.Serializable;

public class RouteId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String originIata;
	private String destIata;
	private String uniqueCarrier;
	
	public RouteId(String origin, String dest, String uniqueCarrier){
		this.originIata = origin;
		this.destIata = dest;
		this.uniqueCarrier = uniqueCarrier;
	}
	
	public String getOriginIata() {
		return originIata;
	}

	public void setOriginIata(String originIata) {
		this.originIata = originIata;
	}

	public String getDestIata() {
		return destIata;
	}

	public void setDestIata(String destIata) {
		this.destIata = destIata;
	}

	public String getUniqueCarrier() {
		return uniqueCarrier;
	}

	public void setUniqueCarrier(String uniqueCarrier) {
		this.uniqueCarrier = uniqueCarrier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destIata == null) ? 0 : destIata.hashCode());
		result = prime * result + ((originIata == null) ? 0 : originIata.hashCode());
		result = prime * result + ((uniqueCarrier == null) ? 0 : uniqueCarrier.hashCode());
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
		RouteId other = (RouteId) obj;
		if (destIata == null) {
			if (other.destIata != null)
				return false;
		} else if (!destIata.equals(other.destIata))
			return false;
		if (originIata == null) {
			if (other.originIata != null)
				return false;
		} else if (!originIata.equals(other.originIata))
			return false;
		if (uniqueCarrier == null) {
			if (other.uniqueCarrier != null)
				return false;
		} else if (!uniqueCarrier.equals(other.uniqueCarrier))
			return false;
		return true;
	}
}
