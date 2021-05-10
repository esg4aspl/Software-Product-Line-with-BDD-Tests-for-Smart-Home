package stepdefinitions;

import business.OutputBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_OFF_BELL_EXPECTATION = "turn off bell";
	private static final String TURN_OFF_BELL_OUTPUT = "Bell responding to TURN_OFF=All";
	

	
	@Then("turn off bell")
	public void turn_off_bell() {
		consoleExpectations.add(TURN_OFF_BELL_EXPECTATION);
		wait(75);
		assertTrue(extractOutputs().size() > 0);
		route();
	}
	
	private void route() {
		original();
		checkTurnOffBell();
	}
	
	private void checkTurnOffBell() {
		check(TURN_OFF_BELL_EXPECTATION, TURN_OFF_BELL_OUTPUT);
	}
	
}