package markersJob.model;

import java.io.Serializable;

public class MarkerInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String markerCodeOrigin;
	private String label;
	private String latitude;
	private String longitude;

	public MarkerInfo(String markerCodeOrigin, String label, String latitude, String longitude){
		this.markerCodeOrigin = markerCodeOrigin;
		this.label = label;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMarkerCodeOrigin() {
		return markerCodeOrigin;
	}

	public void setMarkerCodeOrigin(String markerCodeOrigin) {
		this.markerCodeOrigin = markerCodeOrigin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((markerCodeOrigin == null) ? 0 : markerCodeOrigin.hashCode());
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
		MarkerInfo other = (MarkerInfo) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (markerCodeOrigin == null) {
			if (other.markerCodeOrigin != null)
				return false;
		} else if (!markerCodeOrigin.equals(other.markerCodeOrigin))
			return false;
		return true;
	}
}