package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_AUTOMATED_AIR_CONDITIONING_EXPECTATION = "turn on air conditioning automatic";
	private static final String TURN_OFF_AUTOMATED_AIR_CONDITIONING_EXPECTATION = "turn off air conditioning automatic";
	
	private static final String TURN_ON_AUTOMATED_AIR_CONDITIONING_OUTPUT = "AIR_CONDITIONING_CONTROL responding to TURN_ON=All";
	private static final String TURN_OFF_AUTOMATED_AIR_CONDITIONING_OUTPUT = "AIR_CONDITIONING_CONTROL responding to TURN_OFF=All";
	
	private void route() {
		original();
		checkTurnOnAirConditioningAutomatic();
		checkTurnOffAirConditioningAutomatic();
	}
	
	private void checkTurnOnAirConditioningAutomatic() {
		check(TURN_ON_AUTOMATED_AIR_CONDITIONING_EXPECTATION, TURN_ON_AUTOMATED_AIR_CONDITIONING_OUTPUT);
	}
	
	private void checkTurnOffAirConditioningAutomatic() {
		check(TURN_OFF_AUTOMATED_AIR_CONDITIONING_EXPECTATION, TURN_OFF_AUTOMATED_AIR_CONDITIONING_OUTPUT);
	}
	
	@When("turn on air conditioning automatic")
	public void turn_on_air_conditioning_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:TEMPERATURE=31@HOME");
		consoleExpectations.add(TURN_ON_AUTOMATED_AIR_CONDITIONING_EXPECTATION);
	}
	
	@When("turn off air conditioning automatic")
	public void turn_off_air_conditioning_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:TEMPERATURE=22@HOME");
		consoleExpectations.add(TURN_OFF_AUTOMATED_AIR_CONDITIONING_EXPECTATION);
	}
	
}