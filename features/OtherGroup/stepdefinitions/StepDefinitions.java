package stepdefinitions;

import static org.junit.Assert.assertFalse;

import java.util.List;

import business.OutputBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	@When("call other group for fire")
	public void call_other_group_for_fire() {
	    copyCallOtherGroupForFire();
	}
	
	private void injectFireExpectations() {
		original();
		consoleExpectations.add(CALL_OTHER_GROUP_EXPECTATION);
	}
	
	private void copyCallOtherGroupForFire() {
		assertFalse(fireDepartmentOutputCache.isEmpty());
		assertFalse(fireDepartmentOutputCache.contains(CALL_OTHER_GROUP_OUTPUT));

		firePrecedesCheck(CALL_DEPT_EXPECTATION, CALL_DEPT_OUTPUT, CALL_OTHER_GROUP_EXPECTATION, CALL_OTHER_GROUP_OUTPUT);
		firePrecedesCheck(TURN_ON_SIREN_EXPECTATION, TURN_ON_SIREN_OUTPUT, CALL_OTHER_GROUP_EXPECTATION, CALL_OTHER_GROUP_OUTPUT);
		
		fireDepartmentOutputCache.add(CALL_OTHER_GROUP_OUTPUT);
	}
	
	private void route() {
		original();
		checkCallOtherGroupForFire();
	}
	
	private void checkCallOtherGroupForFire() {
		if (check(CALL_OTHER_GROUP_EXPECTATION, CALL_OTHER_GROUP_OUTPUT))
			assertTrue(fireDepartmentOutputCache.contains(CALL_OTHER_GROUP_OUTPUT));
	}

}