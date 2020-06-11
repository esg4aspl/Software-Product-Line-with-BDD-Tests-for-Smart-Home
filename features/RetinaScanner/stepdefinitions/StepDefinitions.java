package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String RETINA_SCANNER_CORRECT_INPUT = "n-8475yt8374c3b8-34bc384ygcn-3u4ry";
	private static final String RETINA_SCANNER_EXPECTATION = "use retina scanner for authentication";
	private static final String RETINA_SCANNER_OUTPUT = "Authenticated with retina scanner!";

	private void route() {
		original();
		checkUseRetinaScannerForAuthentication();
	}
	
	private void checkUseRetinaScannerForAuthentication() {
		check(RETINA_SCANNER_EXPECTATION, RETINA_SCANNER_OUTPUT);
	}
	
	@When("use retina scanner for authentication")
	public void use_retina_scanner_for_authentication() {
	    readyForAction();
	    app.home.authenticate(RETINA_SCANNER_CORRECT_INPUT);
	    consoleExpectations.add(RETINA_SCANNER_EXPECTATION);
	}
	
}