package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_BLINDS_SIMULATION_EXPECTATION = "turn on blinds simulation";
	private static final String TURN_OFF_BLINDS_SIMULATION_EXPECTATION = "turn off blinds simulation";
	private static final String TURN_ON_BLINDS_SIMULATION_OUTPUT = "PRESENCE_SIMULATION responding to TURN_ON=All";
	private static final String TURN_OFF_BLINDS_SIMULATION_OUTPUT = "PRESENCE_SIMULATION responding to TURN_OFF=All";

	@When("turn on blinds simulation")
	public void turn_on_blinds_simulation() {
		readyForAction();
		p.publish("BLINDS_SIMULATION", "TURN_ON=All@BLINDS_SIMULATION");
		consoleExpectations.add(TURN_ON_BLINDS_SIMULATION_EXPECTATION);
	}

	@When("turn off blinds simulation")
	public void turn_off_blinds_simulation() {
		readyForAction();
		p.publish("BLINDS_SIMULATION", "TURN_OFF=All@BLINDS_SIMULATION");
		consoleExpectations.add(TURN_OFF_BLINDS_SIMULATION_EXPECTATION);
	}
	
	
	private void route() {
		original();
		checkTurnOnBlindsSimulation();
		checkTurnOffBlindsSimulation();
	}
	
	private void checkTurnOnBlindsSimulation() {
		check(TURN_ON_BLINDS_SIMULATION_EXPECTATION, TURN_ON_BLINDS_SIMULATION_OUTPUT);
	}
	
	private void checkTurnOffBlindsSimulation() {
		check(TURN_OFF_BLINDS_SIMULATION_EXPECTATION, TURN_OFF_BLINDS_SIMULATION_OUTPUT);
	}

}