package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String BRIGHTEN_MOODS_EXPECTATION = "brighten moods automatic";
	private static final String DIM_MOODS_EXPECTATION = "dim moods automatic";
	private static final String TURN_ON_MOODS_EXPECTATION = "turn on moods automatic";
	private static final String TURN_OFF_MOODS_EXPECTATION = "turn off moods automatic";
	
	private static final String BRIGHTEN_MOODS_OUTPUT = "MOODS_MANAGEMENT responding to TURN_ON=Brighten";
	private static final String DIM_MOODS_OUTPUT = "MOODS_MANAGEMENT responding to TURN_ON=Dim";
	private static final String TURN_ON_MOODS_OUTPUT = "MOODS_MANAGEMENT responding to TURN_ON=Turn On";
	private static final String TURN_OFF_MOODS_OUTPUT = "MOODS_MANAGEMENT responding to TURN_OFF=Turn Off";
	
	private void route() {
		original();
		checkBrightenMoodsAutomated();
		checkDimMoodsAutomated();
		checkTurnOnMoodsAutomated();
		checkTurnOffMoodsAutomated();
	}
	
	private void checkBrightenMoodsAutomated() {
		check(BRIGHTEN_MOODS_EXPECTATION, BRIGHTEN_MOODS_OUTPUT);
	}
	
	private void checkDimMoodsAutomated() {
		check(DIM_MOODS_EXPECTATION, DIM_MOODS_OUTPUT);
	}
	
	private void checkTurnOnMoodsAutomated() {
		check(TURN_ON_MOODS_EXPECTATION, TURN_ON_MOODS_OUTPUT);
	}
	
	private void checkTurnOffMoodsAutomated() {
		check(TURN_OFF_MOODS_EXPECTATION, TURN_OFF_MOODS_OUTPUT);
	}
	
	@When("brighten moods automatic")
	public void brighten_moods_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=16:00@HOME");
		consoleExpectations.add(BRIGHTEN_MOODS_EXPECTATION);
	}
	
	@When("dim moods automatic")
	public void dim_moods_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=20:00@HOME");	
		consoleExpectations.add(DIM_MOODS_EXPECTATION);
	}
	
	@When("turn on moods automatic")
	public void turn_on_moods_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=07:00@HOME");
		consoleExpectations.add(TURN_ON_MOODS_EXPECTATION);
	}
	
	@When("turn off moods automatic")
	public void turn_off_moods_automatic() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=22:00@HOME");
		consoleExpectations.add(TURN_OFF_MOODS_EXPECTATION);
	}

}