package stepdefinitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import business.EncryptedInternetBag;
import business.InternetBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String DES_INTERNET_EXPECTATION = "send DES encrypted input message";
	private static final String DES_INTERNET_OUTPUT = "INTERNET responding to input.";

	
	@When("send DES encrypted input message")
	public void send_DES_encrypted_input_message() {
		p.publish("INTERNET", "INTERNET=EncryptedInput@INTERNET");
		consoleExpectations.add(DES_INTERNET_EXPECTATION);
		wait(50);
		List<String> bagMessages = InternetBag.getInstance().getMessages();
		assertFalse(bagMessages.get(bagMessages.size() - 1).contains("error"));
	}
	
	@Then("send DES encrypted output message")
	public void send_DES_encrypted_output_message() {
		assertTrue(InternetBag.getInstance().isEmpty());
		assertTrue(extractEncryptedInternetMessages().size() > 0);
		assertTrue(lastEncryptedInternetMessage().equals("INTERNET creates DES encrypted response.")); 
	}
	
	private void route() {
		original();
		checkDESEncrypedOutputMessage();
	}
	
	private void checkDESEncrypedOutputMessage() {
		if (check(DES_INTERNET_EXPECTATION, DES_INTERNET_OUTPUT)) {
			assertTrue(EncryptedInternetBag.getInstance().isEmpty());
		}
	}
	
}