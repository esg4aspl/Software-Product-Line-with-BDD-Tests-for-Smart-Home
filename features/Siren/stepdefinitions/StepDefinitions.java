package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_SIREN_MANUALLY_EXPECTATION = "turn on siren manually";
	private static final String TURN_ON_SIREN_MANUALLY_OUTPUT = "Siren responding to TURN_ON=Garage";
	private static final String TURN_OFF_SIREN_MANUALLY_EXPECTATION = "turn off siren manually";
	private static final String TURN_OFF_SIREN_MANUALLY_OUTPUT = "Siren responding to TURN_OFF=Garage";
	
	@When("turn on siren manual")
	public void turn_on_siren_manual() {
		readyForAction();
		p.publish("SIREN", "TURN_ON=Garage@SIREN");
		consoleExpectations.add(TURN_ON_SIREN_MANUALLY_EXPECTATION);
		wait(75);
	}
	
	@Then("turn off siren manual")
	public void turn_off_siren_manual() {
		readyForAction();
		p.publish("SIREN", "TURN_OFF=Garage@SIREN");
		consoleExpectations.add(TURN_OFF_SIREN_MANUALLY_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnSirenManually();
		checkTurnOffSirenManually();
	}
	
	private void checkTurnOnSirenManually() {
		check(TURN_ON_SIREN_MANUALLY_EXPECTATION, TURN_ON_SIREN_MANUALLY_OUTPUT);
	}
	
	private void checkTurnOffSirenManually() {
		check(TURN_OFF_SIREN_MANUALLY_EXPECTATION, TURN_OFF_SIREN_MANUALLY_OUTPUT);
	}
	
}