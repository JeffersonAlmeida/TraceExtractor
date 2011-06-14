package traceextractor.tracemodel;

public enum EventType {
	METHOD_ENTRY, METHOD_EXIT, CONSTRUCTOR_ENTRY, CONSTRUCTOR_EXIT;
	
	public boolean isEntryEvent() {
		return equals(METHOD_ENTRY) || equals(CONSTRUCTOR_ENTRY); 
	}
	
	public boolean isConstructor() {
		return equals(CONSTRUCTOR_ENTRY) || equals(METHOD_EXIT);
	}
}
