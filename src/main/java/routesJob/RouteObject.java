package routesJob;

//import java.util.StringTokenizer;

public class RouteObject {

	private String origin;
	private String originCity;
	private String originCoordinates;
	//private String originLatitude;
	//private String originLongitude;

	private String dest;
	private String destCity;
	private String destCoordinates;
	//private String destLatitude;
	//private String destLongitude;

	public RouteObject(String origin, String originCity, String originCoordinates, String dest, String destCity, String destCoordinates){
		this.origin = origin;
		this.originCity = originCity;
		this.originCoordinates = originCoordinates;
		/*StringTokenizer tokenizer1 = new StringTokenizer(originCoordinates,";");
		this.originLatitude = tokenizer1.nextToken();
		this.originLongitude = tokenizer1.nextToken();*/
		this.dest = dest;
		this.destCity = destCity;
		this.destCoordinates = destCoordinates;
		/*StringTokenizer tokenizer2 = new StringTokenizer(destCoordinates,";");
		this.destLatitude = tokenizer2.nextToken();
		this.destLongitude = tokenizer2.nextToken();*/
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

	public String getOriginCoordinates() {
		return originCoordinates;
	}

	public void setOriginCoordinates(String originCoordinates) {
		this.originCoordinates = originCoordinates;
	}

	public String getDestCoordinates() {
		return destCoordinates;
	}

	public void setDestCoordinates(String destCoordinates) {
		this.destCoordinates = destCoordinates;
	}
	

	@Override
	public String toString() {
		return "RouteObject [origin=" + origin + ", originCity=" + originCity + ", originCoordinates="
				+ originCoordinates + ", dest=" + dest + ", destCity=" + destCity + ", destCoordinates="
				+ destCoordinates + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dest == null) ? 0 : dest.hashCode());
		result = prime * result + ((destCity == null) ? 0 : destCity.hashCode());
		result = prime * result + ((destCoordinates == null) ? 0 : destCoordinates.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((originCity == null) ? 0 : originCity.hashCode());
		result = prime * result + ((originCoordinates == null) ? 0 : originCoordinates.hashCode());
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
		if (destCoordinates == null) {
			if (other.destCoordinates != null)
				return false;
		} else if (!destCoordinates.equals(other.destCoordinates))
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
		if (originCoordinates == null) {
			if (other.originCoordinates != null)
				return false;
		} else if (!originCoordinates.equals(other.originCoordinates))
			return false;
		return true;
	}
	
}
