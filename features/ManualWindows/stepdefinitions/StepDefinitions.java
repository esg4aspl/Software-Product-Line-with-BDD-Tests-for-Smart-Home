package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String OPEN_WINDOWS_MANUAL_EXPECTATION = "open windows manual";
	private static final String CLOSE_WINDOWS_MANUAL_EXPECTATION = "close windows manual";
	private static final String OPEN_WINDOWS_MANUAL_OUTPUT = "WINDOWS_MANAGEMENT responding to TURN_ON=All";
	private static final String CLOSE_WINDOWS_MANUAL_OUTPUT = "WINDOWS_MANAGEMENT responding to TURN_OFF=All";

	@When("open windows manual")
	public void open_windows_manual() {
		readyForAction();
		p.publish("MANUAL_WINDOWS", "TURN_ON=All@MANUAL_WINDOWS");
		consoleExpectations.add(OPEN_WINDOWS_MANUAL_EXPECTATION);
	}

	@When("close windows manual")
	public void close_windows_manual() {
		readyForAction();
		p.publish("MANUAL_WINDOWS", "TURN_OFF=All@MANUAL_WINDOWS");
		consoleExpectations.add(CLOSE_WINDOWS_MANUAL_EXPECTATION);
	}
	
	
	private void route() {
		original();
		checkTurnOnWindowsManual();
		checkTurnOffWindowsManual();
	}
	
	private void checkTurnOnWindowsManual() {
		check(OPEN_WINDOWS_MANUAL_EXPECTATION, OPEN_WINDOWS_MANUAL_OUTPUT);
	}
	
	private void checkTurnOffWindowsManual() {
		check(CLOSE_WINDOWS_MANUAL_EXPECTATION, CLOSE_WINDOWS_MANUAL_OUTPUT);
	}

}