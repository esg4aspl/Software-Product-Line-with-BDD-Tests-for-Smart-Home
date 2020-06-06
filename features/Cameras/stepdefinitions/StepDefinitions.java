package stepdefinitions;

import java.util.List;

import business.OutputBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private List<String> camerasOutputCache;
	private static final String TURN_ON_LIGHTS_EXPECTATION = "turn on lights";
	private static final String TURN_ON_LIGHTS_OUTPUT = "Lights responding to TURN_ON=Living Room";
	
	public StepDefinitions() {
		camerasOutputCache = new ArrayList<String>();
	}
	
	@When("intrusion detected via cameras")
	public void intrusion_detected_via_cameras() {
		readyForAction();
		p.publish("HOME", "ENV:CAMERAS=Living Room@HOME");
		consoleExpectations.add(TURN_ON_LIGHTS_EXPECTATION);
	}
	
	@When("turn on lights")
	public void turn_on_lights() {
	    copyTurnOnLights();
	}
	
	public void copyTurnOnLights() {
		assertFalse(camerasOutputCache.contains(TURN_ON_LIGHTS_OUTPUT));
		assertTrue(OutputBag.getInstance().getOutputs().contains(TURN_ON_LIGHTS_OUTPUT));
		camerasOutputCache.add(TURN_ON_LIGHTS_OUTPUT);
	}

	private void route() {
		original();
		checkTurnOnLights();
	}
	
	private void checkTurnOnLights() {
		if (check(TURN_ON_LIGHTS_EXPECTATION, TURN_ON_LIGHTS_OUTPUT))
			assertTrue(camerasOutputCache.contains(TURN_ON_LIGHTS_OUTPUT));
	}
	
}