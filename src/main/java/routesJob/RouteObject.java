package routesJob;

import java.util.StringTokenizer;

public class RouteObject {
	
	private String origin;
	private String originCity;
	private Double originLatitude;
	private Double originLongitude;

	private String dest;
	private String destCity;
	private Double destLatitude;
	private Double destLongitude;

	public RouteObject(String origin, String originCity, String originCoordinates, String dest, String destCity, String destCoordinates){
		this.origin = origin;
		this.originCity = originCity;
		
		if(originCoordinates != null){
			StringTokenizer tokenizer1 = new StringTokenizer(originCoordinates,";");
			this.originLatitude = Double.parseDouble(tokenizer1.nextToken());
			this.originLongitude = Double.parseDouble(tokenizer1.nextToken());
		}else{
			this.originLatitude = null;
			this.originLongitude = null;
		}
		
		this.dest = dest;
		this.destCity = destCity;
		
		if(destCoordinates != null){
			StringTokenizer tokenizer2 = new StringTokenizer(destCoordinates,";");
			this.destLatitude = Double.parseDouble(tokenizer2.nextToken());
			this.destLongitude = Double.parseDouble(tokenizer2.nextToken());
		}else{
			this.destLatitude = null;
			this.destLongitude = null;
		}
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	public Double getOriginLatitude() {
		return originLatitude;
	}

	public void setOriginLatitude(Double originLatitude) {
		this.originLatitude = originLatitude;
	}

	public Double getOriginLongitude() {
		return originLongitude;
	}

	public void setOriginLongitude(Double originLongitude) {
		this.originLongitude = originLongitude;
	}

	public Double getDestLatitude() {
		return destLatitude;
	}

	public void setDestLatitude(Double destLatitude) {
		this.destLatitude = destLatitude;
	}

	public Double getDestLongitude() {
		return destLongitude;
	}

	public void setDestLongitude(Double destLongitude) {
		this.destLongitude = destLongitude;
	}
	
	@Override
	public String toString() {
		return "RouteObject [origin=" + origin + ", originCity=" + originCity + ", originLatitude=" + originLatitude
				+ ", originLongitude=" + originLongitude + ", dest=" + dest + ", destCity=" + destCity
				+ ", destLatitude=" + destLatitude + ", destLongitude=" + destLongitude + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dest == null) ? 0 : dest.hashCode());
		result = prime * result + ((destCity == null) ? 0 : destCity.hashCode());
		result = prime * result + ((destLatitude == null) ? 0 : destLatitude.hashCode());
		result = prime * result + ((destLongitude == null) ? 0 : destLongitude.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((originCity == null) ? 0 : originCity.hashCode());
		result = prime * result + ((originLatitude == null) ? 0 : originLatitude.hashCode());
		result = prime * result + ((originLongitude == null) ? 0 : originLongitude.hashCode());
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
		RouteObject other = (RouteObject) obj;
		if (dest == null) {
			if (other.dest != null)
				return false;
		} else if (!dest.equals(other.dest))
			return false;
		if (destCity == null) {
			if (other.destCity != null)
				return false;
		} else if (!destCity.equals(other.destCity))
			return false;
		if (destLatitude == null) {
			if (other.destLatitude != null)
				return false;
		} else if (!destLatitude.equals(other.destLatitude))
			return false;
		if (destLongitude == null) {
			if (other.destLongitude != null)
				return false;
		} else if (!destLongitude.equals(other.destLongitude))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (originCity == null) {
			if (other.originCity != null)
				return false;
		} else if (!originCity.equals(other.originCity))
			return false;
		if (originLatitude == null) {
			if (other.originLatitude != null)
				return false;
		} else if (!originLatitude.equals(other.originLatitude))
			return false;
		if (originLongitude == null) {
			if (other.originLongitude != null)
				return false;
		} else if (!originLongitude.equals(other.originLongitude))
			return false;
		return true;
	}

}
