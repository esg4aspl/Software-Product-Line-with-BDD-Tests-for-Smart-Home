package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_LIGHT_MANUAL_EXPECTATION = "turn on light manual";
	private static final String TURN_OFF_LIGHT_MANUAL_EXPECTATION = "turn off light manual";
	private static final String TURN_ON_LIGHT_MANUAL_OUTPUT = "LIGHT_MANAGEMENT responding to TURN_ON=All";
	private static final String TURN_OFF_LIGHT_MANUAL_OUTPUT = "LIGHT_MANAGEMENT responding to TURN_OFF=All";

	@When("turn on light manual")
	public void turn_on_light_manual() {
		readyForAction();
		p.publish("MANUAL_ILLUMINATION", "TURN_ON=All@MANUAL_ILLUMINATION");
		consoleExpectations.add(TURN_ON_LIGHT_MANUAL_EXPECTATION);
	}

	@When("turn off light manual")
	public void turn_off_light_manual() {
		readyForAction();
		p.publish("MANUAL_ILLUMINATION", "TURN_OFF=All@MANUAL_ILLUMINATION");
		consoleExpectations.add(TURN_OFF_LIGHT_MANUAL_EXPECTATION);
	}
	
	
	private void route() {
		original();
		checkTurnOnLightManual();
		checkTurnOffLightManual();
	}
	
	private void checkTurnOnLightManual() {
		check(TURN_ON_LIGHT_MANUAL_EXPECTATION, TURN_ON_LIGHT_MANUAL_OUTPUT);
	}
	
	private void checkTurnOffLightManual() {
		check(TURN_OFF_LIGHT_MANUAL_EXPECTATION, TURN_OFF_LIGHT_MANUAL_OUTPUT);
	}

}