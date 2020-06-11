package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_HEATING_MANUAL_EXPECTATION = "turn on heating manual";
	private static final String TURN_OFF_HEATING_MANUAL_EXPECTATION = "turn off heating manual";
	private static final String TURN_ON_HEATING_MANUAL_OUTPUT = "HEATING_CONTROL responding to TURN_ON=All";
	private static final String TURN_OFF_HEATING_MANUAL_OUTPUT = "HEATING_CONTROL responding to TURN_OFF=All";
	
	@When("turn off heating manual")
	public void turn_off_heating_manual() {
		readyForAction();
		p.publish("MANUAL_HEATING", "TURN_OFF=All@MANUAL_HEATING");
		consoleExpectations.add(TURN_OFF_HEATING_MANUAL_EXPECTATION);
	}
	
	@When("turn on heating manual")
	public void turn_on_heating_manual() {
		readyForAction();
		p.publish("MANUAL_HEATING", "TURN_ON=All@MANUAL_HEATING");
		consoleExpectations.add(TURN_ON_HEATING_MANUAL_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnHeatingManual();
		checkTurnOffHeatingManual();
	}
	
	private void checkTurnOnHeatingManual() {
		check(TURN_ON_HEATING_MANUAL_EXPECTATION, TURN_ON_HEATING_MANUAL_OUTPUT);
	}
	
	private void checkTurnOffHeatingManual() {
		check(TURN_OFF_HEATING_MANUAL_EXPECTATION, TURN_OFF_HEATING_MANUAL_OUTPUT);
	}

}