package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String OPEN_WINDOWS_AUTOMATIC_EXPECTATION = "open windows automatic";
	private static final String CLOSE_WINDOWS_AUTOMATIC_EXPECTATION = "close windows automatic";
	private static final String OPEN_WINDOWS_AUTOMATIC_OUTPUT = "WINDOWS_MANAGEMENT responding to TURN_ON=All";
	private static final String CLOSE_WINDOWS_AUTOMATIC_OUTPUT = "WINDOWS_MANAGEMENT responding to TURN_OFF=All";

	@When("close windows automatic")
	public void close_windows_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=19:00@HOME");
		consoleExpectations.add(CLOSE_WINDOWS_AUTOMATIC_EXPECTATION);
	}
	
	@When("open windows automatic")
	public void open_windows_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=09:00@HOME");
		consoleExpectations.add(OPEN_WINDOWS_AUTOMATIC_EXPECTATION);
	}
	
	private void route() {
		original();
		checkOpenWindowsAutomatic();
		checkCloseWindowsAutomatic();
	}
	
	private void checkOpenWindowsAutomatic() {
		check(OPEN_WINDOWS_AUTOMATIC_EXPECTATION, OPEN_WINDOWS_AUTOMATIC_OUTPUT);
	}
	
	private void checkCloseWindowsAutomatic() {
		check(CLOSE_WINDOWS_AUTOMATIC_EXPECTATION, CLOSE_WINDOWS_AUTOMATIC_OUTPUT);
	}

}