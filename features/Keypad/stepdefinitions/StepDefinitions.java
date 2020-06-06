package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String KEYPAD_CORRECT_INPUT = "123456";
	private static final String KEYPAD_EXPECTATION = "use keypad for authentication";
	private static final String KEYPAD_OUTPUT = "Authenticated with keypad!";

	private void route() {
		original();
		checkUseKeypadForAuthentication();
	}
	
	private void checkUseKeypadForAuthentication() {
		check(KEYPAD_EXPECTATION, KEYPAD_OUTPUT);
	}
	
	@When("use keypad for authentication")
	public void use_keypad_for_authentication() {
	    readyForAction();
	    app.home.authenticate(KEYPAD_CORRECT_INPUT);
	    consoleExpectations.add(KEYPAD_EXPECTATION);
	}
	
}