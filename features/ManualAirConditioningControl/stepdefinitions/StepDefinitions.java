package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_AIR_CONDITIONING_MANUAL_EXPECTATION = "turn on air conditioning manual";
	private static final String TURN_OFF_AIR_CONDITIONING_MANUAL_EXPECTATION = "turn off air conditioning manual";
	private static final String TURN_ON_AIR_CONDITIONING_MANUAL_OUTPUT = "AIR_CONDITIONING_CONTROL responding to TURN_ON=All";
	private static final String TURN_OFF_AIR_CONDITIONING_MANUAL_OUTPUT = "AIR_CONDITIONING_CONTROL responding to TURN_OFF=All";
	
	@When("turn off air conditioning manual")
	public void turn_off_air_conditioning_manual() {
		readyForAction();
		p.publish("MANUAL_AIR_CONDITIONING_CONTROL", "TURN_OFF=All@MANUAL_AIR_CONDITIONING_CONTROL");
		consoleExpectations.add(TURN_OFF_AIR_CONDITIONING_MANUAL_EXPECTATION);
	}
	
	@When("turn on air conditioning manual")
	public void turn_on_air_conditioning_manual() {
		readyForAction();
		p.publish("MANUAL_AIR_CONDITIONING_CONTROL", "TURN_ON=All@MANUAL_AIR_CONDITIONING_CONTROL");
		consoleExpectations.add(TURN_ON_AIR_CONDITIONING_MANUAL_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnAirConditioningManual();
		checkTurnOffAirConditioningManual();
	}
	
	private void checkTurnOnAirConditioningManual() {
		check(TURN_ON_AIR_CONDITIONING_MANUAL_EXPECTATION, TURN_ON_AIR_CONDITIONING_MANUAL_OUTPUT);
	}
	
	private void checkTurnOffAirConditioningManual() {
		check(TURN_OFF_AIR_CONDITIONING_MANUAL_EXPECTATION, TURN_OFF_AIR_CONDITIONING_MANUAL_OUTPUT);
	}

}