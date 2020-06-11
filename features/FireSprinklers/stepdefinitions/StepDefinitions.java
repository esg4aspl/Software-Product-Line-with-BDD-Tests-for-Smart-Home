package stepdefinitions;

import java.util.List;

import business.OutputBag;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private static final String CLOSE_FIRE_SPRINKLERS_EXPECTATION = "close fire sprinklers";
	private static final String CLOSE_FIRE_SPRINKLERS_OUTPUT = "FIRE_SPRINKLERS responding to TURN_OFF=All";
	
	@When("close fire sprinklers")
	public void close_fire_sprinklers() {
	    readyForAction();
	    p.publish("FIRE_SPRINKLERS", "TURN_OFF=All@FIRE_SPRINKLERS");	    
	    consoleExpectations.add(CLOSE_FIRE_SPRINKLERS_EXPECTATION);
	}
	

	@Then("open fire sprinklers")
	public void open_fire_sprinklers() {
		copyOpenFireSprinklers();
	}
	
	private void injectFireExpectations() {
		original();
		consoleExpectations.add(OPEN_FIRE_SPRINKLERS_EXPECTATION);
	}
	
	private void copyOpenFireSprinklers() {
		assertFalse(fireDepartmentOutputCache.isEmpty());
		assertFalse(fireDepartmentOutputCache.contains(OPEN_FIRE_SPRINKLERS_OUTPUT));

		firePrecedesCheck(CALL_DEPT_EXPECTATION, CALL_DEPT_OUTPUT, OPEN_FIRE_SPRINKLERS_EXPECTATION, OPEN_FIRE_SPRINKLERS_OUTPUT);
		firePrecedesCheck(TURN_ON_SIREN_EXPECTATION, TURN_ON_SIREN_OUTPUT, OPEN_FIRE_SPRINKLERS_EXPECTATION, OPEN_FIRE_SPRINKLERS_OUTPUT);
		firePrecedesCheck(CALL_OTHER_GROUP_EXPECTATION, CALL_OTHER_GROUP_OUTPUT, OPEN_FIRE_SPRINKLERS_EXPECTATION, OPEN_FIRE_SPRINKLERS_OUTPUT);
		
		fireDepartmentOutputCache.add(OPEN_FIRE_SPRINKLERS_OUTPUT);
	}
	
	
	private void route() {
		original();
		checkOpenFireSprinklers();
		checkCloseFireSprinklers();
	}
	
	private void checkOpenFireSprinklers() {
		if (check(OPEN_FIRE_SPRINKLERS_EXPECTATION, OPEN_FIRE_SPRINKLERS_OUTPUT))
			assertTrue(fireDepartmentOutputCache.contains(OPEN_FIRE_SPRINKLERS_OUTPUT));
	}
	
	private void checkCloseFireSprinklers() {
		check(CLOSE_FIRE_SPRINKLERS_EXPECTATION, CLOSE_FIRE_SPRINKLERS_OUTPUT);
	}

}