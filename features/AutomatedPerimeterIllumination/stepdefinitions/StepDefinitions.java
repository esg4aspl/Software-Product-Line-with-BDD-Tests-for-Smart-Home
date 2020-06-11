package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_PERIMETER_LIGHT_AUTOMATIC_EXPECTATION = "turn on perimeter light automatic";
	private static final String TURN_OFF_PERIMETER_LIGHT_AUTOMATIC_EXPECTATION = "turn off perimeter light automatic";
	private static final String TURN_ON_PERIMETER_LIGHT_AUTOMATIC_OUTPUT = "LIGHT_MANAGEMENT responding to TURN_ON=All Perimeter";
	private static final String TURN_OFF_PERIMETER_LIGHT_AUTOMATIC_OUTPUT = "LIGHT_MANAGEMENT responding to TURN_OFF=All Perimeter";

	@When("turn on perimeter light automatic")
	public void turn_on_perimeter_light_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=21:00@HOME");
		consoleExpectations.add(TURN_ON_PERIMETER_LIGHT_AUTOMATIC_EXPECTATION);
	}

	@When("turn off perimeter light automatic")
	public void turn_off_perimeter_light_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=09:00@HOME");
		consoleExpectations.add(TURN_OFF_PERIMETER_LIGHT_AUTOMATIC_EXPECTATION);
	}
	
	
	private void route() {
		original();
		checkTurnOnPerimeterLightAutomatic();
		checkTurnOffPerimeterLightAutomatic();
	}
	
	private void checkTurnOnPerimeterLightAutomatic() {
		check(TURN_ON_PERIMETER_LIGHT_AUTOMATIC_EXPECTATION, TURN_ON_PERIMETER_LIGHT_AUTOMATIC_OUTPUT);
	}
	
	private void checkTurnOffPerimeterLightAutomatic() {
		check(TURN_OFF_PERIMETER_LIGHT_AUTOMATIC_EXPECTATION, TURN_OFF_PERIMETER_LIGHT_AUTOMATIC_OUTPUT);
	}

}