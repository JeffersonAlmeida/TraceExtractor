package traceextractor.monitor.aspectj;

import java.io.*;

import traceextractor.tracemodel.TraceRegister;

public abstract class FileTraceRegister implements TraceRegister {
	public static final String TOKEN_SEP = ",";
	public static final String MARK_INDICATOR = "#";
	private File outputDir;
	private PrintWriter writer;
	
	public FileTraceRegister(File outputDir) {
		this.outputDir = outputDir;
	}
	
	protected abstract File getOutputFile();
	
	public File getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(File outputDir) {
		this.outputDir = outputDir;
	}
	
	public void terminate() {
		if (null != writer) {
			writer.flush();
			writer.close();
		}
	}
	
	protected PrintWriter getWriter() {
		if (null == writer) {
			try {
				writer = new PrintWriter(getOutputFile());
			} catch (FileNotFoundException ex) {
				System.out.println("Cannot find file: " + getOutputDir());
			}
			
		}
		return writer;
	}
}
