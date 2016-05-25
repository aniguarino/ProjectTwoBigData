package routesJob.model;

import java.io.Serializable;

public class RouteId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String originIata;
	private String destIata;
	
	public RouteId(String origin, String dest){
		this.originIata = origin;
		this.destIata = dest;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destIata == null) ? 0 : destIata.hashCode());
		result = prime * result + ((originIata == null) ? 0 : originIata.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "RouteId [originIata=" + originIata + ", destIata=" + destIata + "]";
	}
	
}
