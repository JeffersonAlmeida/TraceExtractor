package traceextractor.tracemodel;

public class StartMark extends Mark {

	public StartMark(String name, long time) {
		super(name,time);
	}
	
	public String type() {
		return "START";
	}

}
