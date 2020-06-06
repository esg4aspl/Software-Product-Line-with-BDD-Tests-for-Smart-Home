package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_VENTILATION_REMOTE_EXPECTATION = "turn on ventilation remote";
	private static final String TURN_OFF_VENTILATION_REMOTE_EXPECTATION = "turn off ventilation remote";
	private static final String TURN_ON_VENTILATION_REMOTE_OUTPUT = "VENTILATION_CONTROL responding to TURN_ON=All";
	private static final String TURN_OFF_VENTILATION_REMOTE_OUTPUT = "VENTILATION_CONTROL responding to TURN_OFF=All";
	
	@When("turn off ventilation remote")
	public void turn_off_ventilation_remote() {
		readyForAction();
		p.publish("REMOTE_VENTILATION_CONTROL", "TURN_OFF=All@REMOTE_VENTILATION_CONTROL");
		consoleExpectations.add(TURN_OFF_VENTILATION_REMOTE_EXPECTATION);
	}
	
	@When("turn on ventilation remote")
	public void turn_on_ventilation_remote() {
		readyForAction();
		p.publish("REMOTE_VENTILATION_CONTROL", "TURN_ON=All@REMOTE_VENTILATION_CONTROL");
		consoleExpectations.add(TURN_ON_VENTILATION_REMOTE_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnVentilationRemote();
		checkTurnOffVentilationRemote();
	}
	
	private void checkTurnOnVentilationRemote() {
		check(TURN_ON_VENTILATION_REMOTE_EXPECTATION, TURN_ON_VENTILATION_REMOTE_OUTPUT);
	}
	
	private void checkTurnOffVentilationRemote() {
		check(TURN_OFF_VENTILATION_REMOTE_EXPECTATION, TURN_OFF_VENTILATION_REMOTE_OUTPUT);
	}

}