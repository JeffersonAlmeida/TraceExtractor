package traceextractor.tracemodel;

import java.io.*;

import org.aspectj.lang.JoinPoint;

public abstract interface TraceFactory {
	TraceRegister createRegister(File outputDir);
	EventData createEventData(JoinPoint joinPoint, EventType eventType, int nestingLevel);
	ExtractorControlGui createControlGui();
}
