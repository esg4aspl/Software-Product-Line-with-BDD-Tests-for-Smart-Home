package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_AIR_CONDITIONING_REMOTE_EXPECTATION = "turn on air conditioning remote";
	private static final String TURN_OFF_AIR_CONDITIONING_REMOTE_EXPECTATION = "turn off air conditioning remote";
	private static final String TURN_ON_AIR_CONDITIONING_REMOTE_OUTPUT = "AIR_CONDITIONING_CONTROL responding to TURN_ON=All";
	private static final String TURN_OFF_AIR_CONDITIONING_REMOTE_OUTPUT = "AIR_CONDITIONING_CONTROL responding to TURN_OFF=All";
	
	@When("turn off air conditioning remote")
	public void turn_off_air_conditioning_remote() {
		readyForAction();
		p.publish("REMOTE_AIR_CONDITIONING_CONTROL", "TURN_OFF=All@REMOTE_AIR_CONDITIONING_CONTROL");
		consoleExpectations.add(TURN_OFF_AIR_CONDITIONING_REMOTE_EXPECTATION);
	}
	
	@When("turn on air conditioning remote")
	public void turn_on_air_conditioning_remote() {
		readyForAction();
		p.publish("REMOTE_AIR_CONDITIONING_CONTROL", "TURN_ON=All@REMOTE_AIR_CONDITIONING_CONTROL");
		consoleExpectations.add(TURN_ON_AIR_CONDITIONING_REMOTE_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnAirConditioningRemote();
		checkTurnOffAirConditioningRemote();
	}
	
	private void checkTurnOnAirConditioningRemote() {
		check(TURN_ON_AIR_CONDITIONING_REMOTE_EXPECTATION, TURN_ON_AIR_CONDITIONING_REMOTE_OUTPUT);
	}
	
	private void checkTurnOffAirConditioningRemote() {
		check(TURN_OFF_AIR_CONDITIONING_REMOTE_EXPECTATION, TURN_OFF_AIR_CONDITIONING_REMOTE_OUTPUT);
	}

}