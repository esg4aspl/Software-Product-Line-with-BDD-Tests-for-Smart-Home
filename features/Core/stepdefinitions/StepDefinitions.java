package stepdefinitions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.ArrayList;

import application.App;
import business.OutputBag;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.en.*;
import redis.clients.jedis.Jedis;

/**
 * TODO description
 */
public class StepDefinitions {
	
	private List<String> consoleOutputs;
	private List<String> consoleExpectations;
	private Publisher p;
	private App app;
	
	public StepDefinitions() {
		this.app = new App();
		this.app.init();
		this.p = new Publisher();
		this.consoleOutputs = new ArrayList<String>();
		this.consoleExpectations = new ArrayList<String>();
	}

	@Then("turn on")
	public void turn_on() {
		try {			
			app.home.init();
		} catch (Exception e) {
			fail();
		}
		consoleExpectations.add("turn on");
	}
	
	@Then("turn off")
	public void turn_off() {
		readyForAction();
	    try {
			app.home.turnOff();
		} catch (Exception e) {
			fail();
		}
	}
	
	@When("output to console")
	public void output_to_console() {
		assertTrue(extractOutputs().size() > 0);
		route();
	}
	
	private void route() {
	    checkTurnOn();
	}
	
	private boolean check(String eName, String oName) {
		if (!consoleExpectations.contains(eName))
			return false;
		assertTrue(consoleOutputs.contains(oName));
		consoleExpectations.remove(eName);
		consoleOutputs.remove(oName);
		System.out.println("Checked " + eName + " -- " + oName);
		return true;
	}
	
	private void checkTurnOn() {
		check("turn on", "STARTED HOME");
	}
	
	private void readyForAction() {
		assertTrue(app.home.isStarted());
		assertFalse(app.home.isTurnedOff());
		noPendingOutputs();
	}
	
	private void noPendingOutputs() {
		assertTrue(OutputBag.getInstance().isEmpty());
	}
	
	@After
	public void after(Scenario scenario) {
		if (app.home.isTurnedOff()) {
			noPendingOutputs();
		}
		app.kill();
		OutputBag.getInstance().clearOutputs();
	}
	
	@AfterStep
	public void afterStep() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Given("The environment is set up with {string}")
	public void the_environment_is_set_up_with(String string) {
		//Empty
	}
	
	@When("[")
	public void begin () {
		//Empty
	}
	
	private void fail() {
		assertTrue(false);
	}
	
	protected class Publisher extends Thread {
		
		public void publish(String channel, String message) {
			Jedis jPublisher = null;
			try {
				jPublisher = App.getJedis();
				jPublisher.publish(channel, message);
			} finally {
				if (jPublisher != null)
					jPublisher.close();

			}
		}
		
		@Override
		public void run() {
			super.run();
		}
	}

	private List<String> extractOutputs() {
		List<String> newOutputs = OutputBag.getInstance().clearOutputs();
		for (String s : newOutputs)
			consoleOutputs.add(s);
		return newOutputs;
	}

}