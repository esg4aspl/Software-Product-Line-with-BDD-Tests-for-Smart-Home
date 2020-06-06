package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_VENTILATION_MANUAL_EXPECTATION = "turn on ventilation manual";
	private static final String TURN_OFF_VENTILATION_MANUAL_EXPECTATION = "turn off ventilation manual";
	private static final String TURN_ON_VENTILATION_MANUAL_OUTPUT = "VENTILATION_CONTROL responding to TURN_ON=All";
	private static final String TURN_OFF_VENTILATION_MANUAL_OUTPUT = "VENTILATION_CONTROL responding to TURN_OFF=All";
	
	@When("turn off ventilation manual")
	public void turn_off_ventilation_manual() {
		readyForAction();
		p.publish("MANUAL_VENTILATION_CONTROL", "TURN_OFF=All@MANUAL_VENTILATION_CONTROL");
		consoleExpectations.add(TURN_OFF_VENTILATION_MANUAL_EXPECTATION);
	}
	
	@When("turn on ventilation manual")
	public void turn_on_ventilation_manual() {
		readyForAction();
		p.publish("MANUAL_VENTILATION_CONTROL", "TURN_ON=All@MANUAL_VENTILATION_CONTROL");
		consoleExpectations.add(TURN_ON_VENTILATION_MANUAL_EXPECTATION);
	}
	
	private void route() {
		original();
		checkTurnOnVentilationManual();
		checkTurnOffVentilationManual();
	}
	
	private void checkTurnOnVentilationManual() {
		check(TURN_ON_VENTILATION_MANUAL_EXPECTATION, TURN_ON_VENTILATION_MANUAL_OUTPUT);
	}
	
	private void checkTurnOffVentilationManual() {
		check(TURN_OFF_VENTILATION_MANUAL_EXPECTATION, TURN_OFF_VENTILATION_MANUAL_OUTPUT);
	}

}