package routesJob;

public class airportInfo {
	private String name;
	private Double latitude;
	private Double longitude;

	public airportInfo(String name, Double latitudeDouble, Double longitudeDouble){
		this.name = name;
		this.latitude = latitudeDouble;
		this.longitude = longitudeDouble;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
