package ghostFlightJob.model;

import java.io.Serializable;
import java.util.Date;

public class FlightInfo implements Serializable, Comparable<FlightInfo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date dateFlight;
	private String origin;
	private String dest;
	
	public FlightInfo(Date dateFlight, String origin, String dest) {
		super();
		this.dateFlight = dateFlight;
		this.origin = origin;
		this.dest = dest;
	}

	public Date getDateFlight() {
		return dateFlight;
	}

	public void setDateFlight(Date dateFlight) {
		this.dateFlight = dateFlight;
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

	@Override
	public int compareTo(FlightInfo o) {
		return this.dateFlight.compareTo(o.getDateFlight());
	}

	@Override
	public String toString() {
		return "FlightInfo [dateFlight=" + dateFlight + ", origin=" + origin + ", dest=" + dest + "]";
	}
}
