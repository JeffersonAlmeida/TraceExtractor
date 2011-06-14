package traceextractor.monitor.aspectj;

import java.util.*;

import traceextractor.tracemodel.*;

/**
 * Capture system execution events and forward to tracer threads.
 */
public aspect Monitor {
//  MEMBERS
	private TraceFactory factory;
    private ExtractorControlGui controlGui;
    private Map<Thread,TracerThread> tracersMap;
    {
    	factory = new TraceFactoryImpl();
    	tracersMap = new HashMap<Thread,TracerThread>();
    	controlGui = factory.createControlGui();
    	controlGui.getOutputDir();
    }
    
// POINTCUTS
    pointcut alocacaoDinamica(Object objeto) :
        initialization(*.new (..))
        && target(objeto)
        && !within(traceextractor..*);
    
    pointcut execucaoDeMetodo() :
    	execution(* *(..))
    	&& !within(traceextractor..*);
    
// ADVICES
    before() : 
    	execucaoDeMetodo() {
    	
   		tracer().registerEvent(thisJoinPoint, EventType.METHOD_ENTRY);
    }
    
    after() : 
    	execucaoDeMetodo() {
    	
   		tracer().registerEvent(thisJoinPoint, EventType.METHOD_EXIT);
    }
    
    // Registrar Criação de Objetos
    before(Object objeto) : 
    	alocacaoDinamica(objeto) {
    	
    	tracer().registerEvent(thisJoinPoint, EventType.CONSTRUCTOR_ENTRY);
    }
    
    after(Object objeto) :
    	alocacaoDinamica(objeto) {
    	
    	tracer().registerEvent(thisJoinPoint, EventType.CONSTRUCTOR_EXIT);
    }
    
// METHODS
    public TracerThread tracer() {
    	Thread current = Thread.currentThread();
    	TracerThread tracer = null;
//    	synchronized(this) {
    		tracer = tracersMap.get(current);
    		if (null == tracer) {
    			tracer = new TracerThread(current, controlGui.getOutputDir(), factory);
    			tracersMap.put(current, tracer);
    			tracer.start();
    		}
//    	}
    	return tracer;
    }
}
