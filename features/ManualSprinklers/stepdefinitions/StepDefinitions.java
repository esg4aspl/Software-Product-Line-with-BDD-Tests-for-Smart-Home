package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_IRRIGATION_SPRINKLERS_MANUAL_EXPECTATION = "open irrigation sprinklers manual";
	private static final String TURN_OFF_IRRIGATION_SPRINKLERS_MANUAL_EXPECTATION = "close irrigation sprinklers manual";
	private static final String TURN_ON_IRRIGATION_SPRINKLERS_MANUAL_OUTPUT = "IRRIGATION_SPRINKLERS responding to TURN_ON=All";
	private static final String TURN_OFF_IRRIGATION_SPRINKLERS_MANUAL_OUTPUT = "IRRIGATION_SPRINKLERS responding to TURN_OFF=All";

	@When("turn off irrigation sprinklers manual")
	public void turn_off_irrigation_sprinklers_manual() {
		readyForAction();
		p.publish("MANUAL_SPRINKLERS", "TURN_OFF=All@MANUAL_SPRINKLERS");
		consoleExpectations.add(TURN_OFF_IRRIGATION_SPRINKLERS_MANUAL_EXPECTATION);
	}
	
	@When("turn on irrigation sprinklers manual")
	public void turn_on_irrigation_sprinklers_manual() {
		readyForAction();
		p.publish("MANUAL_SPRINKLERS", "TURN_ON=All@MANUAL_SPRINKLERS");
		consoleExpectations.add(TURN_ON_IRRIGATION_SPRINKLERS_MANUAL_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnIrrigationSprinklersManual();
		checkTurnOffIrrigationSprinklersManual();
	}
	
	private void checkTurnOnIrrigationSprinklersManual() {
		check(TURN_ON_IRRIGATION_SPRINKLERS_MANUAL_EXPECTATION, TURN_ON_IRRIGATION_SPRINKLERS_MANUAL_OUTPUT);
	}
	
	private void checkTurnOffIrrigationSprinklersManual() {
		check(TURN_OFF_IRRIGATION_SPRINKLERS_MANUAL_EXPECTATION, TURN_OFF_IRRIGATION_SPRINKLERS_MANUAL_OUTPUT);
	}

}