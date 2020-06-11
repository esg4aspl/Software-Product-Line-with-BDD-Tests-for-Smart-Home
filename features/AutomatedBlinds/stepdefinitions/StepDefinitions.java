package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String OPEN_BLINDS_AUTOMATIC_EXPECTATION = "open blinds automatic";
	private static final String CLOSE_BLINDS_AUTOMATIC_EXPECTATION = "close blinds automatic";
	private static final String OPEN_BLINDS_AUTOMATIC_OUTPUT = "BLINDS_MANAGEMENT responding to TURN_ON=All";
	private static final String CLOSE_BLINDS_AUTOMATIC_OUTPUT = "BLINDS_MANAGEMENT responding to TURN_OFF=All";

	@When("close blinds automatic")
	public void close_blinds_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=19:00@HOME");
		consoleExpectations.add(CLOSE_BLINDS_AUTOMATIC_EXPECTATION);
	}
	
	@When("open blinds automatic")
	public void open_blinds_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=09:00@HOME");
		consoleExpectations.add(OPEN_BLINDS_AUTOMATIC_EXPECTATION);
	}
	
	private void route() {
		original();
		checkOpenBlindsAutomatic();
		checkCloseBlindsAutomatic();
	}
	
	private void checkOpenBlindsAutomatic() {
		check(OPEN_BLINDS_AUTOMATIC_EXPECTATION, OPEN_BLINDS_AUTOMATIC_OUTPUT);
	}
	
	private void checkCloseBlindsAutomatic() {
		check(CLOSE_BLINDS_AUTOMATIC_EXPECTATION, CLOSE_BLINDS_AUTOMATIC_OUTPUT);
	}

}