package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_AV_SIMULATION_EXPECTATION = "turn on AV simulation";
	private static final String TURN_OFF_AV_SIMULATION_EXPECTATION = "turn off AV simulation";
	private static final String TURN_ON_AV_SIMULATION_OUTPUT = "PRESENCE_SIMULATION responding to TURN_ON=All";
	private static final String TURN_OFF_AV_SIMULATION_OUTPUT = "PRESENCE_SIMULATION responding to TURN_OFF=All";

	@When("turn on AV simulation")
	public void turn_on_AV_simulation() {
		readyForAction();
		p.publish("AV_SIMULATION", "TURN_ON=All@AV_SIMULATION");
		consoleExpectations.add(TURN_ON_AV_SIMULATION_EXPECTATION);
	}

	@When("turn off AV simulation")
	public void turn_off_AV_simulation() {
		readyForAction();
		p.publish("AV_SIMULATION", "TURN_OFF=All@AV_SIMULATION");
		consoleExpectations.add(TURN_OFF_AV_SIMULATION_EXPECTATION);
	}
	
	
	private void route() {
		original();
		checkTurnOnAVSimulation();
		checkTurnOffAVSimulation();
	}
	
	private void checkTurnOnAVSimulation() {
		check(TURN_ON_AV_SIMULATION_EXPECTATION, TURN_ON_AV_SIMULATION_OUTPUT);
	}
	
	private void checkTurnOffAVSimulation() {
		check(TURN_OFF_AV_SIMULATION_EXPECTATION, TURN_OFF_AV_SIMULATION_OUTPUT);
	}

}