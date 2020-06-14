package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_BELL_MANUALLY_EXPECTATION = "turn on bell manually";
	private static final String TURN_ON_BELL_MANUALLY_OUTPUT = "Bell responding to TURN_ON=Garage";
	private static final String TURN_OFF_BELL_MANUALLY_EXPECTATION = "turn off bell manually";
	private static final String TURN_OFF_BELL_MANUALLY_OUTPUT = "Bell responding to TURN_OFF=Garage";
	
	@When("turn on bell manual")
	public void turn_on_bell_manual() {
		readyForAction();
		p.publish("BELL", "TURN_ON=Garage@BELL");
		consoleExpectations.add(TURN_ON_BELL_MANUALLY_EXPECTATION);
		wait(75);
	}
	
	@Then("turn off bell manual")
	public void turn_off_bell_manual() {
		readyForAction();
		p.publish("BELL", "TURN_OFF=Garage@BELL");
		consoleExpectations.add(TURN_OFF_BELL_MANUALLY_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnBellManually();
		checkTurnOffBellManually();
	}
	
	private void checkTurnOnBellManually() {
		check(TURN_ON_BELL_MANUALLY_EXPECTATION, TURN_ON_BELL_MANUALLY_OUTPUT);
	}
	
	private void checkTurnOffBellManually() {
		check(TURN_OFF_BELL_MANUALLY_EXPECTATION, TURN_OFF_BELL_MANUALLY_OUTPUT);
	}
	
}