package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_LIGHTS_MANUALLY_EXPECTATION = "turn on lights manually";
	private static final String TURN_ON_LIGHTS_MANUALLY_OUTPUT = "Lights responding to TURN_ON=Garage";
	private static final String TURN_OFF_LIGHTS_MANUALLY_EXPECTATION = "turn off lights manually";
	private static final String TURN_OFF_LIGHTS_MANUALLY_OUTPUT = "Lights responding to TURN_OFF=Garage";
	
	@When("turn on lights manual")
	public void turn_on_lights_manual() {
		readyForAction();
		p.publish("LIGHTS", "TURN_ON=Garage@LIGHTS");
		consoleExpectations.add(TURN_ON_LIGHTS_MANUALLY_EXPECTATION);
		wait(75);
	}
	
	@Then("turn off lights manual")
	public void turn_off_lights_manual() {
		readyForAction();
		p.publish("LIGHTS", "TURN_OFF=Garage@LIGHTS");
		consoleExpectations.add(TURN_OFF_LIGHTS_MANUALLY_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnLightsManually();
		checkTurnOffLightsManually();
	}
	
	private void checkTurnOnLightsManually() {
		check(TURN_ON_LIGHTS_MANUALLY_EXPECTATION, TURN_ON_LIGHTS_MANUALLY_OUTPUT);
	}
	
	private void checkTurnOffLightsManually() {
		check(TURN_OFF_LIGHTS_MANUALLY_EXPECTATION, TURN_OFF_LIGHTS_MANUALLY_OUTPUT);
	}
	
}