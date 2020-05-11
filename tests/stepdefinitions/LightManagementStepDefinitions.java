package stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.OutputStream;
import java.io.PrintStream;

import application.App;
import business.Command;
import business.IPublisher;
import cucumber.api.Scenario;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import redis.clients.jedis.Jedis;

public class LightManagementStepDefinitions {
	
	String log;
	private String[] steps;
	Publisher p;
	App app;
	int stepCounter;
	
	@Before
	public void before(Scenario scenario) {
		stepCounter = 0;
	}
	
	@AfterStep
	public void afterStep() {
		try {
			stepCounter++;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	//Private Methods
	private String nextStep() {
		if (stepCounter < steps.length-1)
			return steps[stepCounter+1];
		return "";
	}
	
	private String previousStep() {
		if (stepCounter > 0)
			return steps[stepCounter-1-1];
		return "";
	}
	
	@Given("The environment is set up with {string}")
	public void the_environment_is_set_up_with(String string) {
		steps = string.split(",");

	    PrintStream origOut = System.out;
	    PrintStream interceptor = new Interceptor(origOut);
	    System.setOut(interceptor);
	    
	    app = new App();
		app.init();
		p = new Publisher();
	}

	@When("[")
	public void begin() {
	    
	}

	@Then("Turn on")
	public void turn_on() {
	    app.home.init();
	}

	@When("UI output")
	public void ui_output() {
		testprint(previousStep());
		switch(previousStep()) {
		
			case "Turn on": assertEquals(log, "STARTED HOME"); break;
			case "Automated inhouse light on": assertEquals(log, "LIGHT_MANAGEMENT responding to TURN_ON=All Inhouse"); break;
			case "Automated inhouse light off": assertEquals(log, "LIGHT_MANAGEMENT responding to TURN_OFF=All Inhouse"); break;
			case "Automated perimeter light on": assertEquals(log, "LIGHT_MANAGEMENT responding to TURN_ON=All Perimeter"); break;
			case "Automated perimeter light off": assertEquals(log, "LIGHT_MANAGEMENT responding to TURN_OFF=All Perimeter"); break;
			case "Manual light on": assertEquals(log, "LIGHT_MANAGEMENT responding to TURN_ON=All Inhouse"); break;
			case "Manual light off": assertEquals(log, "LIGHT_MANAGEMENT responding to TURN_OFF=All Inhouse"); break;
			case "Turn off": assertEquals(log, "STOPPED HOME"); break;
			
		}
	    
	}

	@Then("Automated inhouse light on")
	public void automated_inhouse_light_on() {
	    p.publish("HOME", "ENV:CLOCK=07:00@HOME");
	}

	@When("Automated inhouse light off")
	public void automated_inhouse_light_off() {
		p.publish("HOME", "ENV:CLOCK=23:00@HOME");
	}

	@Then("Automated perimeter light on")
	public void automated_perimeter_light_on() {
		p.publish("HOME", "ENV:CLOCK=21:00@HOME");
	}

	@Then("Automated perimeter light off")
	public void automated_perimeter_light_off() {
		p.publish("HOME", "ENV:CLOCK=09:00@HOME");
	}
	
	@Then("Manual light on")
	public void manual_light_on() {
		p.publish("MANUAL_ILLUMINATION", "TURN_ON=All Inhouse@MANUAL_ILLUMINATION");
	}

	@Then("Manual light off")
	public void manual_light_off() {
		p.publish("MANUAL_ILLUMINATION", "TURN_OFF=All Inhouse@MANUAL_ILLUMINATION");
	}

	@Then("Turn off")
	public void turn_off() {
	    app.home.kill();
	}

	private class Interceptor extends PrintStream {
	    public Interceptor(OutputStream out) {
	        super(out, true);
	    }
	    @Override
	    public void println(String s) {
	        log = s;
	    }
	}
	
	private void testprint(String s) {
		System.out.print(s + "\n");
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
				try {
					Thread.sleep(500); //Wait so the messages can travel
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		@Override
		public void run() {
			super.run();
		}
	}
	
	
	

}
