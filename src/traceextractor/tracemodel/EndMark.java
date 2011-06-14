package traceextractor.tracemodel;

public class EndMark extends Mark {

	public EndMark(String name, long time) {
		super(name, time);
	}
	
	public String type() {
		return "END";
	}

}
