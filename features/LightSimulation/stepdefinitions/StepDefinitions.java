package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String TURN_ON_LIGHT_SIMULATION_EXPECTATION = "turn on light simulation";
	private static final String TURN_OFF_LIGHT_SIMULATION_EXPECTATION = "turn off light simulation";
	private static final String TURN_ON_LIGHT_SIMULATION_OUTPUT = "PRESENCE_SIMULATION responding to TURN_ON=All";
	private static final String TURN_OFF_LIGHT_SIMULATION_OUTPUT = "PRESENCE_SIMULATION responding to TURN_OFF=All";

	@When("turn on light simulation")
	public void turn_on_light_simulation() {
		readyForAction();
		p.publish("LIGHT_SIMULATION", "TURN_ON=All@LIGHT_SIMULATION");
		consoleExpectations.add(TURN_ON_LIGHT_SIMULATION_EXPECTATION);
	}

	@When("turn off light simulation")
	public void turn_off_light_simulation() {
		readyForAction();
		p.publish("LIGHT_SIMULATION", "TURN_OFF=All@LIGHT_SIMULATION");
		consoleExpectations.add(TURN_OFF_LIGHT_SIMULATION_EXPECTATION);
	}
	
	
	private void route() {
		original();
		checkTurnOnLightSimulation();
		checkTurnOffLightSimulation();
	}
	
	private void checkTurnOnLightSimulation() {
		check(TURN_ON_LIGHT_SIMULATION_EXPECTATION, TURN_ON_LIGHT_SIMULATION_OUTPUT);
	}
	
	private void checkTurnOffLightSimulation() {
		check(TURN_OFF_LIGHT_SIMULATION_EXPECTATION, TURN_OFF_LIGHT_SIMULATION_OUTPUT);
	}

}