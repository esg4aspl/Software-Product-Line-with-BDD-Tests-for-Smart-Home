package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_AUTOMATED_VENTILATION_EXPECTATION = "turn on ventilation automatic";
	private static final String TURN_OFF_AUTOMATED_VENTILATION_EXPECTATION = "turn off ventilation automatic";
	
	private static final String TURN_ON_AUTOMATED_VENTILATION_OUTPUT = "VENTILATION_CONTROL responding to TURN_ON=All";
	private static final String TURN_OFF_AUTOMATED_VENTILATION_OUTPUT = "VENTILATION_CONTROL responding to TURN_OFF=All";
	
	private void route() {
		original();
		checkTurnOnVentilationAutomatic();
		checkTurnOffVentilationAutomatic();
	}
	
	private void checkTurnOnVentilationAutomatic() {
		check(TURN_ON_AUTOMATED_VENTILATION_EXPECTATION, TURN_ON_AUTOMATED_VENTILATION_OUTPUT);
	}
	
	private void checkTurnOffVentilationAutomatic() {
		check(TURN_OFF_AUTOMATED_VENTILATION_EXPECTATION, TURN_OFF_AUTOMATED_VENTILATION_OUTPUT);
	}
	
	@When("turn on ventilation automatic")
	public void turn_on_ventilation_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:AIR=Dirty@HOME");
		consoleExpectations.add(TURN_ON_AUTOMATED_VENTILATION_EXPECTATION);
	}
	
	@When("turn off ventilation automatic")
	public void turn_off_ventilation_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:AIR=Clean@HOME");
		consoleExpectations.add(TURN_OFF_AUTOMATED_VENTILATION_EXPECTATION);
	}
	
}