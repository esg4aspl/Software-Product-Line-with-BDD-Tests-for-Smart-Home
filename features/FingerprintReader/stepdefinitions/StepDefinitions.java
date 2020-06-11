package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String FINGERPRINT_READER_CORRECT_INPUT = "gc46tr736t4r6dtux";
	private static final String FINGERPRINT_READER_EXPECTATION = "use fingerprint reader for authentication";
	private static final String FINGERPRINT_READER_OUTPUT = "Authenticated with fingerprint reader!";

	private void route() {
		original();
		checkUseFingerprintReaderForAuthentication();
	}
	
	private void checkUseFingerprintReaderForAuthentication() {
		check(FINGERPRINT_READER_EXPECTATION, FINGERPRINT_READER_OUTPUT);
	}
	
	@When("use fingerprint reader for authentication")
	public void use_fingerprint_reader_for_authentication() {
	    readyForAction();
	    app.home.authenticate(FINGERPRINT_READER_CORRECT_INPUT);
	    consoleExpectations.add(FINGERPRINT_READER_EXPECTATION);
	}
	
}