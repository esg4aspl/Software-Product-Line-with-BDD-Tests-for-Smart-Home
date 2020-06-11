package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_IRRIGATION_SPRINKLERS_AUTOMATIC_EXPECTATION = "open irrigation sprinklers automatic";
	private static final String TURN_OFF_IRRIGATION_SPRINKLERS_AUTOMATIC_EXPECTATION = "close irrigation sprinklers automatic";
	private static final String TURN_ON_IRRIGATION_SPRINKLERS_AUTOMATIC_OUTPUT = "IRRIGATION_SPRINKLERS responding to TURN_ON=All";
	private static final String TURN_OFF_IRRIGATION_SPRINKLERS_AUTOMATIC_OUTPUT = "IRRIGATION_SPRINKLERS responding to TURN_OFF=All";

	@When("turn on irrigation sprinklers automatic")
	public void turn_on_irrigation_sprinklers_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=14:00@HOME");
		consoleExpectations.add(TURN_ON_IRRIGATION_SPRINKLERS_AUTOMATIC_EXPECTATION);
	}
	
	@When("turn off irrigation sprinklers automatic")
	public void turn_off_irrigation_sprinklers_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=15:00@HOME");
		consoleExpectations.add(TURN_OFF_IRRIGATION_SPRINKLERS_AUTOMATIC_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnIrrigationSprinklersAutomatic();
		checkTurnOffIrrigationSprinklersAutomatic();
	}
	
	private void checkTurnOnIrrigationSprinklersAutomatic() {
		check(TURN_ON_IRRIGATION_SPRINKLERS_AUTOMATIC_EXPECTATION, TURN_ON_IRRIGATION_SPRINKLERS_AUTOMATIC_OUTPUT);
	}
	
	private void checkTurnOffIrrigationSprinklersAutomatic() {
		check(TURN_OFF_IRRIGATION_SPRINKLERS_AUTOMATIC_EXPECTATION, TURN_OFF_IRRIGATION_SPRINKLERS_AUTOMATIC_OUTPUT);
	}

}