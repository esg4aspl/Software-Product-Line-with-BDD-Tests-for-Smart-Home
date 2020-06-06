package stepdefinitions;

import static org.junit.Assert.assertTrue;

import business.UIBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TOUCH_SCREEN_EXPECTATION = "input via touchscreen";
	private static final String TOUCH_SCREEN_OUTPUT = "TOUCH_SCREEN responding to TOUCH=True";
	
	@When("input via touchscreen")
	public void input_via_touchscreen() {
		readyForAction();
		p.publish("TOUCH_SCREEN", "TOUCH=True@TOUCH_SCREEN");
		consoleExpectations.add(TOUCH_SCREEN_EXPECTATION);
	}
	
	@Then("output via touchscreen")
	public void output_via_touchscreen() {
		assertTrue(extractUIMessages().size() > 0);
		assertTrue(lastUIMessage().equals(TOUCH_SCREEN_OUTPUT));
	}

	private void route() {
		original();
		checkOutputViaTouchscreen();
	}
	
	private void checkOutputViaTouchscreen() {
		if (check(TOUCH_SCREEN_EXPECTATION, TOUCH_SCREEN_OUTPUT))
			assertTrue(UIBag.getInstance().isEmpty());
	}
	
	
}