package stepdefinitions;

import java.util.List;

import business.OutputBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private List<String> glassbreakSensorOutputCache;
	private static final String TURN_ON_BELL_EXPECTATION = "turn on bell";
	private static final String TURN_ON_BELL_OUTPUT = "Bell responding to TURN_ON=Kitchen";
	
	public StepDefinitions() {
		glassbreakSensorOutputCache = new ArrayList<String>();
	}
	
	@When("intrusion detected via glassbreak sensor")
	public void intrusion_detected_via_glassbreak_sensor() {
		readyForAction();
		p.publish("HOME", "ENV:GLASSBREAK=Kitchen@HOME");
		consoleExpectations.add(TURN_ON_BELL_EXPECTATION);
	}
	
	@When("turn on bell")
	public void turn_on_bell() {
	    copyTurnOnBell();
	}
	
	public void copyTurnOnBell() {
		assertFalse(glassbreakSensorOutputCache.contains(TURN_ON_BELL_OUTPUT));
		assertTrue(OutputBag.getInstance().getOutputs().contains(TURN_ON_BELL_OUTPUT));
		glassbreakSensorOutputCache.add(TURN_ON_BELL_OUTPUT);
	}

	private void route() {
		original();
		checkTurnOnBell();
	}
	
	private void checkTurnOnBell() {
		if (check(TURN_ON_BELL_EXPECTATION, TURN_ON_BELL_OUTPUT))
			assertTrue(glassbreakSensorOutputCache.contains(TURN_ON_BELL_OUTPUT));
	}
	
}