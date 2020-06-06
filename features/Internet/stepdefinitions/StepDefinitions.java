package stepdefinitions;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import business.InternetBag;
import business.UIBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String REGULAR_INTERNET_EXPECTATION = "input via Internet";
	private static final String REGULAR_INTERNET_OUTPUT = "INTERNET responding to input.";
	
	private List<String> internetMessages;
	
	public StepDefinitions() {
		internetMessages = new ArrayList<String>();
	}
	
	@When("input via Internet")
	public void input_via_Internet() {
	    readyForAction();
	    p.publish("INTERNET", "INTERNET=Input@INTERNET");
	    consoleExpectations.add(REGULAR_INTERNET_EXPECTATION);
	    System.out.println();
	}
	
	@Then("output via Internet")
	public void output_via_Internet() {
		assertTrue(extractInternetMessages().size() > 0); 
		assertTrue(lastInternetMessage().equals("INTERNET completed transfer."));
	}
	
	private void route() {
		original();
		checkOutputViaInternet();
	}
	
	private void checkOutputViaInternet() {
		if (check(REGULAR_INTERNET_EXPECTATION, REGULAR_INTERNET_OUTPUT))
			assertTrue(InternetBag.getInstance().isEmpty());
	}
	
	private List<String> extractInternetMessages() {
		List<String> messages = InternetBag.getInstance().clearMessages();
		for (String s : messages) {
			internetMessages.add(s);
		}
		return messages;
	}
	
	private String lastInternetMessage() {
		if (internetMessages.size() == 0)
			return "";
		return internetMessages.get(internetMessages.size() - 1);
	}
	
	private void noPendingInternetMessages() {
		assertTrue(InternetBag.getInstance().isEmpty());
	}
	
	
	public void injectAfter() {
		original();
		if (app.home.isTurnedOff())
			noPendingInternetMessages();
		InternetBag.getInstance().clearMessages();
	}

}