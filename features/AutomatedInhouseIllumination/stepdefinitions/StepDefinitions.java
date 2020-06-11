package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_INHOUSE_LIGHT_AUTOMATIC_EXPECTATION = "turn on inhouse light automatic";
	private static final String TURN_OFF_INHOUSE_LIGHT_AUTOMATIC_EXPECTATION = "turn off inhouse light automatic";
	private static final String TURN_ON_INHOUSE_LIGHT_AUTOMATIC_OUTPUT = "LIGHT_MANAGEMENT responding to TURN_ON=All Inhouse";
	private static final String TURN_OFF_INHOUSE_LIGHT_AUTOMATIC_OUTPUT = "LIGHT_MANAGEMENT responding to TURN_OFF=All Inhouse";

	@When("turn on inhouse light automatic")
	public void turn_on_inhouse_light_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=07:00@HOME");
		consoleExpectations.add(TURN_ON_INHOUSE_LIGHT_AUTOMATIC_EXPECTATION);
	}

	@When("turn off inhouse light automatic")
	public void turn_off_inhouse_light_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=23:00@HOME");
		consoleExpectations.add(TURN_OFF_INHOUSE_LIGHT_AUTOMATIC_EXPECTATION);
	}
	
	
	private void route() {
		original();
		checkTurnOnInhouseLightAutomatic();
		checkTurnOffInhouseLightAutomatic();
	}
	
	private void checkTurnOnInhouseLightAutomatic() {
		check(TURN_ON_INHOUSE_LIGHT_AUTOMATIC_EXPECTATION, TURN_ON_INHOUSE_LIGHT_AUTOMATIC_OUTPUT);
	}
	
	private void checkTurnOffInhouseLightAutomatic() {
		check(TURN_OFF_INHOUSE_LIGHT_AUTOMATIC_EXPECTATION, TURN_OFF_INHOUSE_LIGHT_AUTOMATIC_OUTPUT);
	}

}