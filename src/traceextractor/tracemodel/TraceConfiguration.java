package traceextractor.tracemodel;

import java.util.*;

public class TraceConfiguration {
	private Map<String, String> configurationMap = new HashMap<String, String>();
	{
		setProperty("register_class", "traceextractor.monitor.aspectj.SimpleFileTraceRegister");
	}
	
	public String getProperty(String key) {
		return configurationMap.get(key);
	}
	
	public void setProperty(String key, String value) {
		configurationMap.put(key, value);
	}
}
