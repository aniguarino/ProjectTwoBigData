package ghostFlightJob.model;

import java.io.Serializable;

public class Counters implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer counterGhost;
	private Integer counterAll;
	private String percent;
	
	public Counters(Integer counterGhost, Integer counterAll, String percent) {
		super();
		this.counterGhost = counterGhost;
		this.counterAll = counterAll;
		this.percent = percent;
	}

	public Integer getCounterGhost() {
		return counterGhost;
	}

	public void setCounterGhost(Integer counterGhost) {
		this.counterGhost = counterGhost;
	}

	public Integer getCounterAll() {
		return counterAll;
	}

	public void setCounterAll(Integer counterAll) {
		this.counterAll = counterAll;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}
}
