package traceextractor.monitor.aspectj;

import java.io.File;

import traceextractor.tracemodel.EventData;
import traceextractor.tracemodel.Mark;

public class SimpleMarkRegister extends FileTraceRegister {
	private File outputFile;
	
	public SimpleMarkRegister(File dir) {
		super(dir);
		this.outputFile = new File(dir.getPath(), "trace.mark.txt");
	}
	
	public void registerEvent(EventData eventData) {
	}

	public void registerMark(Mark mark) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(MARK_INDICATOR);
		sb.append(mark.type() + TOKEN_SEP);
		sb.append(mark.getName() + TOKEN_SEP);
		sb.append(mark.getTime());
		getWriter().println(sb.toString());
		getWriter().flush();
	}
	
	protected File getOutputFile() {
		return outputFile;
	}

}
