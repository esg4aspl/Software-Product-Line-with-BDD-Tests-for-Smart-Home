package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_OFF_LIGHTS_EXPECTATION = "turn off lights";
	private static final String TURN_OFF_LIGHTS_OUTPUT = "Lights responding to TURN_OFF=All";
	
	@Then("turn off lights")
	public void turn_off_lights() {
		consoleExpectations.add(TURN_OFF_LIGHTS_EXPECTATION);
		wait(75);
		assertTrue(extractOutputs().size() > 0);
		route();
	}
	
	private void route() {
		original();
		checkTurnOffLights();
	}
	
	private void checkTurnOffLights() {
		check(TURN_OFF_LIGHTS_EXPECTATION, TURN_OFF_LIGHTS_OUTPUT);
	}
	
}