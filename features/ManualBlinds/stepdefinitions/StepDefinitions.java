package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String OPEN_BLINDS_MANUAL_EXPECTATION = "open blinds manual";
	private static final String CLOSE_BLINDS_MANUAL_EXPECTATION = "close blinds manual";
	private static final String OPEN_BLINDS_MANUAL_OUTPUT = "BLINDS_MANAGEMENT responding to TURN_ON=All";
	private static final String CLOSE_BLINDS_MANUAL_OUTPUT = "BLINDS_MANAGEMENT responding to TURN_OFF=All";

	@When("close blinds manual")
	public void close_blinds_manual() {
		readyForAction();
		p.publish("MANUAL_BLINDS", "TURN_OFF=All@MANUAL_BLINDS");
		consoleExpectations.add(CLOSE_BLINDS_MANUAL_EXPECTATION);
	}
	
	@When("open blinds manual")
	public void open_blinds_manual() {
		readyForAction();
		p.publish("MANUAL_BLINDS", "TURN_ON=All@MANUAL_BLINDS");
		consoleExpectations.add(OPEN_BLINDS_MANUAL_EXPECTATION);
	}
	
	private void route() {
		original();
		checkOpenBlindsManual();
		checkCloseBlindsManual();
	}
	
	private void checkOpenBlindsManual() {
		check(OPEN_BLINDS_MANUAL_EXPECTATION, OPEN_BLINDS_MANUAL_OUTPUT);
	}
	
	private void checkCloseBlindsManual() {
		check(CLOSE_BLINDS_MANUAL_EXPECTATION, CLOSE_BLINDS_MANUAL_OUTPUT);
	}

}