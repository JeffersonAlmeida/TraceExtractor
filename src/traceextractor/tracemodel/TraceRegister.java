package traceextractor.tracemodel;

public interface TraceRegister {
	public void registerEvent(EventData eventData);
	public void registerMark(Mark mark);
	public void terminate();
}
