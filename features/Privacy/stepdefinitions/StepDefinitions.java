package stepdefinitions;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import business.EncryptedInternetBag;

/**
 * TODO description
 */
public class StepDefinitions {

	private List<String> encryptedInternetMessages;
	
	public StepDefinitions() {
		encryptedInternetMessages = new ArrayList<String>();
	}
	
	private List<String> extractEncryptedInternetMessages() {
		List<String> messages = EncryptedInternetBag.getInstance().clearMessages();
		for (String s : messages) {
			encryptedInternetMessages.add(s);
		}
		return messages;
	}
	
	private String lastEncryptedInternetMessage() {
		if (encryptedInternetMessages.size() == 0)
			return "";
		return encryptedInternetMessages.get(encryptedInternetMessages.size() - 1);
	}
	
	private void noPendingEncryptedInternetMessages() {
		assertTrue(EncryptedInternetBag.getInstance().isEmpty());
	}
	
	public void injectAfter() {
		original();
		if (app.home.isTurnedOff())
			noPendingEncryptedInternetMessages();
		EncryptedInternetBag.getInstance().clearMessages();
	}

	
}