package stepdefinitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import business.EncryptedInternetBag;
import business.InternetBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String RSA_INTERNET_EXPECTATION = "send RSA encrypted input message";
	private static final String RSA_INTERNET_OUTPUT = "INTERNET responding to input.";

	
	@When("send RSA encrypted input message")
	public void send_RSA_encrypted_input_message() {
		p.publish("INTERNET", "INTERNET=EncryptedInput@INTERNET");
		consoleExpectations.add(RSA_INTERNET_EXPECTATION);
		wait(50);
		List<String> bagMessages = InternetBag.getInstance().getMessages();
		assertFalse(bagMessages.get(bagMessages.size() - 1).contains("error"));
	}
	
	@Then("send RSA encrypted output message")
	public void send_RSA_encrypted_output_message() {
		assertTrue(InternetBag.getInstance().isEmpty());
		assertTrue(extractEncryptedInternetMessages().size() > 0);
		assertTrue(lastEncryptedInternetMessage().equals("INTERNET creates RSA encrypted response.")); 
	}
	
	private void route() {
		original();
		checkRSAEncrypedOutputMessage();
	}
	
	private void checkRSAEncrypedOutputMessage() {
		if (check(RSA_INTERNET_EXPECTATION, RSA_INTERNET_OUTPUT)) {
			assertTrue(EncryptedInternetBag.getInstance().isEmpty());
		}
	}
	
}