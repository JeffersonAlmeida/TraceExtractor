package traceextractor.tracemodel;

public abstract class Mark {
	private String name;
	private long time;
	
	public Mark(String name, long time) {
		this.name = name;
		this.time = time;
	}
	
	public abstract String type();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
