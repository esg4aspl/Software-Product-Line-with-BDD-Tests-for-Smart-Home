package business;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO description
 */
public class InternetBag {

	
	private List<String> messages;
	private static InternetBag instance = null;
	
	private InternetBag() {
		messages = new ArrayList<String>();
	}
	
	public static InternetBag getInstance() {
		if (instance == null)
			instance = new InternetBag();
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
	
	public List<String> getMessages() {
		return messages;
	}
}