package stepdefinitions;

import java.util.List;

import business.UIBag;

import java.util.ArrayList;

/**
 * TODO description
 */
public class StepDefinitions {

	private List<String> uiMessages;
	
	public StepDefinitions() {
		uiMessages = new ArrayList<String>();
	}
	
	private List<String> extractUIMessages() {
		List<String> messages = UIBag.getInstance().clearMessages();
		for (String s : messages) {
			uiMessages.add(s);
		}
		return messages;
	}
	
	private String lastUIMessage() {
		if (uiMessages.size() == 0)
			return "";
		return uiMessages.get(uiMessages.size() - 1);
	}
	
	private void noPendingUIMessages() {
		assertTrue(UIBag.getInstance().isEmpty());
	}
	
	public void injectAfter() {
		original();
		if (app.home.isTurnedOff())
			noPendingUIMessages();
		UIBag.getInstance().clearMessages();
	}
	
}