package stepdefinitions;

import java.util.List;

import business.OutputBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private List<String> fireDepartmentOutputCache;
	private static final String CALL_DEPT_EXPECTATION = "call fire department";
	private static final String TURN_ON_SIREN_EXPECTATION = "turn on siren";
	private static final String CALL_DEPT_OUTPUT = "FIRST_AID_GROUP calls Fire Department.";
	private static final String TURN_ON_SIREN_OUTPUT = "Siren responding to TURN_ON=Fire";
	
	public StepDefinitions() {
		fireDepartmentOutputCache = new ArrayList<String>();
	}
	
	@When("fire detected via smoke detector")
	public void fire_detected_via_smoke_detector() {
		readyForAction();
		p.publish("HOME", "ENV:FIRE=On@HOME");
		consoleExpectations.add(CALL_DEPT_EXPECTATION);
		consoleExpectations.add(TURN_ON_SIREN_EXPECTATION);
	}
	
	@When("call fire department")
	public void call_fire_department() {
		copyCallFireDepartment();
	}
	
	@When("turn on siren")
	public void turn_on_siren() {
		copyTurnOnSiren();
	}
	
	private void route() {
		original();
		checkCallFireDepartment();
		checkTurnOnSiren();
	}
	
	private void copyCallFireDepartment() {
		assertFalse(fireDepartmentOutputCache.contains(CALL_DEPT_OUTPUT));
		assertTrue(OutputBag.getInstance().getOutputs().contains(CALL_DEPT_OUTPUT));
		fireDepartmentOutputCache.add(CALL_DEPT_OUTPUT);
	}
	
	private void copyTurnOnSiren() {
		assertFalse(fireDepartmentOutputCache.contains(TURN_ON_SIREN_OUTPUT));
		List<String> copyOutputs = OutputBag.getInstance().getOutputs();
		assertTrue(fireDepartmentOutputCache.contains(CALL_DEPT_OUTPUT));
		assertTrue(copyOutputs.contains(TURN_ON_SIREN_OUTPUT));
		assertTrue(copyOutputs.indexOf(CALL_DEPT_OUTPUT) < copyOutputs.indexOf(TURN_ON_SIREN_OUTPUT));
		fireDepartmentOutputCache.add(TURN_ON_SIREN_OUTPUT);
	}
	
	private void checkCallFireDepartment() {
		if (check(CALL_DEPT_EXPECTATION, CALL_DEPT_OUTPUT))
			assertTrue(fireDepartmentOutputCache.contains(CALL_DEPT_OUTPUT));
	}
	
	private void checkTurnOnSiren() {
		if (check(TURN_ON_SIREN_EXPECTATION, TURN_ON_SIREN_OUTPUT))
			assertTrue(fireDepartmentOutputCache.contains(TURN_ON_SIREN_OUTPUT));
	}

}