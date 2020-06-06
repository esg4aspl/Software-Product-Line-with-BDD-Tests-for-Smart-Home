package stepdefinitions;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String START_AV_EXPECTATION = "start AV automated";
	private static final String STOP_AV_EXPECTATION = "stop AV automated";
	private static final String TURN_ON_AV_EXPECTATION = "turn on AV automated";
	private static final String TURN_OFF_AV_EXPECTATION = "turn off AV automated";
	
	private static final String START_AV_OUTPUT = "AV_MANAGEMENT responding to TURN_ON=Start";
	private static final String STOP_AV_OUTPUT = "AV_MANAGEMENT responding to TURN_OFF=Stop";
	private static final String TURN_ON_AV_OUTPUT = "AV_MANAGEMENT responding to TURN_ON=Turn On";
	private static final String TURN_OFF_AV_OUTPUT = "AV_MANAGEMENT responding to TURN_OFF=Turn Off";
	
	private void route() {
		original();
		checkStartAVAutomated();
		checkStopAVAutomated();
		checkTurnOnAVAutomated();
		checkTurnOffAVAutomated();
	}
	
	private void checkStartAVAutomated() {
		check(START_AV_EXPECTATION, START_AV_OUTPUT);
	}
	
	private void checkStopAVAutomated() {
		check(STOP_AV_EXPECTATION, STOP_AV_OUTPUT);
	}
	
	private void checkTurnOnAVAutomated() {
		check(TURN_ON_AV_EXPECTATION, TURN_ON_AV_OUTPUT);
	}
	
	private void checkTurnOffAVAutomated() {
		check(TURN_OFF_AV_EXPECTATION, TURN_OFF_AV_OUTPUT);
	}
	
	@When("start AV automated")
	public void start_AV_automated() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=10:00@HOME");
		consoleExpectations.add(START_AV_EXPECTATION);
	}
	
	@When("stop AV automated")
	public void stop_AV_automated() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=19:00@HOME");	
		consoleExpectations.add(STOP_AV_EXPECTATION);
	}
	
	@When("turn on AV automated")
	public void turn_on_AV_automated() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=07:00@HOME");
		consoleExpectations.add(TURN_ON_AV_EXPECTATION);
	}
	
	@When("turn off AV automated")
	public void turn_off_AV_automated() {
		readyForAction();
		p.publish("HOME", "ENV:CLOCK=22:00@HOME");
		consoleExpectations.add(TURN_OFF_AV_EXPECTATION);
	}

}