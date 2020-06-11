package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_HEATING_REMOTE_EXPECTATION = "turn on heating remote";
	private static final String TURN_OFF_HEATING_REMOTE_EXPECTATION = "turn off heating remote";
	private static final String TURN_ON_HEATING_REMOTE_OUTPUT = "HEATING_CONTROL responding to TURN_ON=All";
	private static final String TURN_OFF_HEATING_REMOTE_OUTPUT = "HEATING_CONTROL responding to TURN_OFF=All";
	
	@When("turn off heating remote")
	public void turn_off_heating_remote() {
		readyForAction();
		p.publish("REMOTE_HEATING_CONTROL", "TURN_OFF=All@REMOTE_HEATING_CONTROL");
		consoleExpectations.add(TURN_OFF_HEATING_REMOTE_EXPECTATION);
	}
	
	@When("turn on heating remote")
	public void turn_on_heating_remote() {
		readyForAction();
		p.publish("REMOTE_HEATING_CONTROL", "TURN_ON=All@REMOTE_HEATING_CONTROL");
		consoleExpectations.add(TURN_ON_HEATING_REMOTE_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnHeatingRemote();
		checkTurnOffHeatingRemote();
	}
	
	private void checkTurnOnHeatingRemote() {
		check(TURN_ON_HEATING_REMOTE_EXPECTATION, TURN_ON_HEATING_REMOTE_OUTPUT);
	}
	
	private void checkTurnOffHeatingRemote() {
		check(TURN_OFF_HEATING_REMOTE_EXPECTATION, TURN_OFF_HEATING_REMOTE_OUTPUT);
	}

}