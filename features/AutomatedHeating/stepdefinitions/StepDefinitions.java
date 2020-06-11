package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_AUTOMATED_HEATING_EXPECTATION = "turn on heating automatic";
	private static final String TURN_OFF_AUTOMATED_HEATING_EXPECTATION = "turn off heating automatic";
	
	private static final String TURN_ON_AUTOMATED_HEATING_OUTPUT = "HEATING_CONTROL responding to TURN_ON=All";
	private static final String TURN_OFF_AUTOMATED_HEATING_OUTPUT = "HEATING_CONTROL responding to TURN_OFF=All";
	
	private void route() {
		original();
		checkTurnOnHeatingAutomatic();
		checkTurnOffHeatingAutomatic();
	}
	
	private void checkTurnOnHeatingAutomatic() {
		check(TURN_ON_AUTOMATED_HEATING_EXPECTATION, TURN_ON_AUTOMATED_HEATING_OUTPUT);
	}
	
	private void checkTurnOffHeatingAutomatic() {
		check(TURN_OFF_AUTOMATED_HEATING_EXPECTATION, TURN_OFF_AUTOMATED_HEATING_OUTPUT);
	}
	
	@When("turn on heating automatic")
	public void turn_on_heating_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:TEMPERATURE=20@HOME");
		consoleExpectations.add(TURN_ON_AUTOMATED_HEATING_EXPECTATION);
	}
	
	@When("turn off heating automatic")
	public void turn_off_heating_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:TEMPERATURE=30@HOME");
		consoleExpectations.add(TURN_OFF_AUTOMATED_HEATING_EXPECTATION);
	}
	
}