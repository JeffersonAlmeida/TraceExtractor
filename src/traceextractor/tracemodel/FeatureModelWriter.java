package traceextractor.tracemodel;

import java.io.*;
import java.util.*;
import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FeatureModelWriter {
	
	private static final String FEATURE_MODEL_FILE = "feature_model.txt";
	
	private Set<String> features;
	private File outputDir;
	private PrintWriter writer;
	private String systemName;
	private String systemVersion;
	private Component guiParent;
	
	public FeatureModelWriter(File outputDir, Component guiParent) {
		this.outputDir = outputDir;
		this.features = new LinkedHashSet<String>();
		this.guiParent = guiParent;
	}
	
	public void finalize() {
		if (null != writer) {
			writer.flush();
			writer.close();
		}
	}
	
	public void addFeature(String featureName) {
		if (features.contains(featureName)) {
			System.out.println("Feature allready added: " + featureName);
			return;
		}
		
		features.add(featureName);
		
		writeToFile(featureName);
	}
	
	public Component getGuiParent() {
		return guiParent;
	}

	public void setGuiParent(Component guiParent) {
		this.guiParent = guiParent;
	}
	
	private void writeToFile(String featureName) {
		try {
			getWriter().println("\"" + featureName + "\"");
			getWriter().flush();
		
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private PrintWriter getWriter() throws IOException {
		if (null == writer) {
			writer = new PrintWriter(new File(outputDir, FEATURE_MODEL_FILE));
			writer.println("system=" + getSystemName());
			writer.println("version=" + getSystemVersion());
		}
		
		return writer;
	}
	
	private String getSystemName() {
		if (null == systemName) {
			systemName = JOptionPane.showInputDialog(getGuiParent(), "Inform the system name: ", "SYSTEM_NAME");
		}
		return systemName;
	}
	
	private String getSystemVersion() {
		if (null == systemVersion) {
			systemVersion = JOptionPane.showInputDialog(getGuiParent(), "Inform the system name: ", "SYSTEM_VERSION");
		}
		return systemVersion;
	}
}
