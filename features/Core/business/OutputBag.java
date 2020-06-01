package business;

import java.util.List;
import java.util.ArrayList;

/**
 * TODO description
 */
public class OutputBag {
	
	private List<String> outputs;
	private static OutputBag instance = null;
	
	private OutputBag() {
		outputs = new ArrayList<String>();
	}
	
	public static OutputBag getInstance() {
		if (instance == null)
			instance = new OutputBag();
		return instance;
	}
	
	public void addOutput(String output) {
		outputs.add(output);
	}
	
	public boolean isEmpty() {
		return outputs.size() == 0;
	}
	
	public List<String> clearOutputs() {
		List<String> temp = new ArrayList<String>();
		for (String o : outputs) {
			temp.add(o);
		}
		outputs.clear();
		return temp;
	}

}