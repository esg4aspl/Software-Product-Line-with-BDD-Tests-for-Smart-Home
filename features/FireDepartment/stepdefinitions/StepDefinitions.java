package stepdefinitions;

import static org.junit.Assert.assertFalse;

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
	private static final String CALL_OTHER_GROUP_EXPECTATION = "call other group for fire";
	private static final String CALL_OTHER_GROUP_OUTPUT = "FIRST_AID_GROUP calls Other Group.";
	private static final String OPEN_FIRE_SPRINKLERS_EXPECTATION = "open fire sprinklers";
	private static final String OPEN_FIRE_SPRINKLERS_OUTPUT = "FIRE_SPRINKLERS responding to TURN_ON=All";
	
	public StepDefinitions() {
		fireDepartmentOutputCache = new ArrayList<String>();
	}
	
	@When("fire detected via smoke detector")
	public void fire_detected_via_smoke_detector() {
		readyForAction();
		p.publish("HOME", "ENV:FIRE=On@HOME");
		injectFireExpectations();
		wait(100);
	}
	
	@When("turn on siren")
	public void turn_on_siren() {
	    copyTurnOnSiren();
	}
	
	private void injectFireExpectations() {
		consoleExpectations.add(CALL_DEPT_EXPECTATION);
		consoleExpectations.add(TURN_ON_SIREN_EXPECTATION);
	}
	
	private void firePrecedesCheck(String precedingExpectation, String precedingOutput, String succeedingExpectation, String succeedingOutput) {
		if (!consoleExpectations.contains(precedingExpectation) || !consoleExpectations.contains(succeedingExpectation))
			return;
		List<String> copyOutputs = OutputBag.getInstance().getOutputs();
		
		assertTrue(fireDepartmentOutputCache.contains(precedingOutput));
		assertTrue(copyOutputs.contains(precedingOutput));
		assertTrue(copyOutputs.contains(succeedingOutput));
		assertTrue(copyOutputs.indexOf(precedingOutput) < copyOutputs.indexOf(succeedingOutput));
	}
	
	@When("call fire department")
	public void call_fire_department() {
		copyCallFireDepartment();
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
		// System.out.println("Siren fire control checks");
		assertFalse(fireDepartmentOutputCache.isEmpty());
		assertFalse(fireDepartmentOutputCache.contains(TURN_ON_SIREN_OUTPUT));
		firePrecedesCheck(CALL_DEPT_EXPECTATION, CALL_DEPT_OUTPUT, TURN_ON_SIREN_EXPECTATION, TURN_ON_SIREN_OUTPUT);
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