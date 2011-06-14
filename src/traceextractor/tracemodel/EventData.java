package traceextractor.tracemodel;

public class EventData {
	public static final long STATIC_CALL_OID = -1;
	private long executionTime;
	private long targetOID;
	private long threadId;
	private int nestingLevel;
	private String targetClassName;
	private String eventTypeSignature;
	private EventType eventType;
	private String methodSignature;
	
	public String getMethodSignature() {
		return methodSignature;
	}
	public void setMethodSignature(String methodSignature) {
		this.methodSignature = methodSignature;
	}
	public long getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
	public long getTargetOID() {
		return targetOID;
	}
	public void setTargetOID(long targetOID) {
		this.targetOID = targetOID;
	}
	public String getTargetClassName() {
		return targetClassName;
	}
	public void setTargetClassName(String targetClassName) {
		this.targetClassName = targetClassName;
	}
	public String getEventTypeSignature() {
		return eventTypeSignature;
	}
	public void setEventTypeSignature(String eventTypeSignature) {
		this.eventTypeSignature = eventTypeSignature;
	}
	public long getThreadId() {
		return threadId;
	}
	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}
	public int getNestingLevel() {
		return nestingLevel;
	}
	public void setNestingLevel(int nestingLevel) {
		this.nestingLevel = nestingLevel;
	}
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
}
