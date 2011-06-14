package traceextractor.tracemodel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.IdentityHashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

public class EventDataBuilder {
	private long counter = 0;
	private IdentityHashMap<Object,Long> objectTable = new IdentityHashMap<Object,Long>(); 
	
	private String getTargetClassNameFromJoinPoint(JoinPoint joinPoint) {
		Object target = joinPoint.getTarget(); 
		
		if (null != target) {
			return joinPoint.getTarget().getClass().getName();
		}
		
		return joinPoint.getSignature().getDeclaringType().getName();
	}
	
	private long getTargetOidFromJoinPoint(JoinPoint joinPoint) {
		Object target = joinPoint.getTarget(); 
		
		if (null != target) {
			Long value = objectTable.get(target);
			
			if (null == value) {
				value = ++counter;
				objectTable.put(target, value);
			}
			
			return value.longValue();
		}
		
		return EventData.STATIC_CALL_OID;
	}
	
	private String getEventTypeSignatureFromJoinPoint(JoinPoint joinPoint) {
		String event_sig = null;
		
		if (null != joinPoint.getSignature()) {
			
			if (joinPoint.getSignature() instanceof MethodSignature) {
				Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
				if (null == method) {
					System.out.println("-----------------------------------");
					System.out.println(joinPoint.getSignature().toLongString());
					System.out.println("Null Method - discarting call");
					System.out.println("-----------------------------------");
					System.out.flush();
					return null;
				}
				event_sig = method.getName();
				
			} else if (joinPoint.getSignature() instanceof ConstructorSignature) {
				Constructor constructor = ((ConstructorSignature)joinPoint.getSignature()).getConstructor();
				if (null == constructor) {
					System.out.println("-----------------------------------");
					System.out.println(joinPoint.getSignature().toLongString());
					System.out.println("Null Constructor - discarting call");
					System.out.println("-----------------------------------");
					System.out.flush();
					return null;
				}
				event_sig = constructor.getName();
			
			} else {
				System.out.println("-----------------------------------");
				System.out.println(joinPoint.getSignature().toLongString());
				System.out.println("Unsupported signature!");
				System.out.println("-----------------------------------");
				System.out.flush();
				return null;
			}
		} else {
			System.out.println("-----------------------------------");
			System.out.println(joinPoint.toString());
			System.out.println("Null JoinPoint Signature!");
			System.out.println("-----------------------------------");
			System.out.flush();
		}
		
		return event_sig;
	}
	
	public EventData buildEventData(JoinPoint joinPoint, EventType eventType, int nestingLevel) {
		String event_type_sig = getEventTypeSignatureFromJoinPoint(joinPoint);
		
		if (null == event_type_sig) {
			return null;
		}
		
		//daniel vieira
		Class[] sig = ((CodeSignature)joinPoint.getSignature()).getParameterTypes();
		StringBuilder buf = new StringBuilder();
		if(sig != null) {
			for (int i = 0; i < sig.length; i++) {
				/*String fullName = sig[i].getName();
				if(fullName.indexOf("$") >= 0) {
					fullName = fullName.replace('$', '.');
				}
				Class classParam = sig[i];
				Class innerC = classParam.getEnclosingClass();
				if(innerC != null) {
					buf.append(innerC.getSimpleName());
					buf.append('.');
				}
				buf.append(sig[i].getSimpleName());*/
				
				buf.append(sig[i].getName());
				if(i+1 < sig.length) {
					buf.append('|');  //para nao misturar com o separador do arquivo de trace ao inves de usar , optei por ;
				}
			}
		}
		//
		
		EventData ed = new EventData();
		
		ed.setExecutionTime(System.currentTimeMillis());
		ed.setThreadId(Thread.currentThread().getId());
		ed.setTargetClassName(getTargetClassNameFromJoinPoint(joinPoint));
		ed.setTargetOID(getTargetOidFromJoinPoint(joinPoint));
		ed.setEventTypeSignature(event_type_sig);
		ed.setNestingLevel(nestingLevel);
		ed.setEventType(eventType);
		ed.setMethodSignature(buf.toString());
		return ed;
	}
}
