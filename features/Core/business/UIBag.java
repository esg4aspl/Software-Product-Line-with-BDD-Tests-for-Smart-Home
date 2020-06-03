package business;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO description
 */
public class UIBag {

	
	private List<String> messages;
	private static UIBag instance = null;
	
	private UIBag() {
		messages = new ArrayList<String>();
	}
	
	public static UIBag getInstance() {
		if (instance == null)
			instance = new UIBag();
		return instance;
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
	
	public boolean isEmpty() {
		return messages.size() == 0;
	}
	
	public List<String> clearMessages() {
		List<String> temp = new ArrayList<String>();
		for (String o : messages) {
			temp.add(o);
		}
		messages.clear();
		return temp;
	}
}