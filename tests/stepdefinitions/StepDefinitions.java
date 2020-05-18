package stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import application.App;
import business.Command;
import business.IPublisher;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import redis.clients.jedis.Jedis;

public class StepDefinitions {
	
	String[] logs = new String[20000];
	int logIndex = -1;
	private String[] steps;
	Publisher p;
	App app;
	int stepCounter;
	
	public StepDefinitions() {
	    PrintStream origOut = System.out;
	    PrintStream interceptor = new Interceptor(origOut);
	    System.setOut(interceptor);
	    
	    app = new App();
		app.init();
		p = new Publisher();
	}
	
	private String getLastLog() {
		if (logs[logIndex] == null) {
			return "";
		}
		
		return logs[logIndex];
	}
	
	@Before
	public void before(Scenario scenario) {
		stepCounter = 0;
	}
	
	@After
	public void after(Scenario scenario) {
		app.home.kill();
	}
	
	@AfterStep
	public void afterStep() {
		try {
			Thread.sleep(20); //Wait so the messages can travel
			//System.out.println(logs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		logs = new String[20000];
		logIndex = -1;

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
		List<String> envResults;
		//testprint(previousStep());
		switch(previousStep()) {
		
			case "Turn on": assertEquals("STARTED HOME", getLastLog()); break;
			
			case "Automated inhouse light on":
				envResults = new ArrayList<String>();
				for (int i = logIndex; i >= logIndex-20 && i >= 0; i--) {
					envResults.add(logs[i]);
					if (logs[i].equals("HOME analyzing environment logs: " + "CLOCK=07:00@HOME"))
						break;
				}
				assertTrue(envResults.contains("LIGHT_MANAGEMENT responding to TURN_ON=All Inhouse"));
				break;
				
			case "Automated inhouse light off":
				envResults = new ArrayList<String>();
				for (int i = logIndex; i >= logIndex-20 && i >= 0; i--) {
					envResults.add(logs[i]);
					if (logs[i].equals("HOME analyzing environment logs: " + "CLOCK=23:00@HOME"))
						break;
				}
				assertTrue(envResults.contains("LIGHT_MANAGEMENT responding to TURN_OFF=All Inhouse"));
				break;
			
			case "Automated perimeter light on":
				envResults = new ArrayList<String>();
				for (int i = logIndex; i >= logIndex-20 && i >= 0; i--) {
					envResults.add(logs[i]);
					if (logs[i].equals("HOME analyzing environment logs: " + "CLOCK=21:00@HOME"))
						break;
				}
				assertTrue(envResults.contains("LIGHT_MANAGEMENT responding to TURN_ON=All Perimeter"));
				break;
				
			case "Automated perimeter light off":
				envResults = new ArrayList<String>();
				for (int i = logIndex; i >= logIndex-20 && i >= 0; i--) {
					envResults.add(logs[i]);
					if (logs[i].equals("HOME analyzing environment logs: " + "CLOCK=09:00@HOME"))
						break;
				}
				assertTrue(envResults.contains("LIGHT_MANAGEMENT responding to TURN_OFF=All Perimeter"));
				break;
				
			case "Manual light on": assertEquals("LIGHT_MANAGEMENT responding to TURN_ON=All Inhouse", getLastLog()); break;
			case "Manual light off": assertEquals("LIGHT_MANAGEMENT responding to TURN_OFF=All Inhouse", getLastLog()); break;
			case "Turn off": assertEquals("STOPPED HOME", getLastLog()); break;
			
			//Windows
			case "Manual window open": assertEquals("WINDOWS_MANAGEMENT responding to TURN_ON=All", getLastLog()); break;
			case "Manual window close": assertEquals("WINDOWS_MANAGEMENT responding to TURN_OFF=All", getLastLog()); break;
			case "Automated window open":
				envResults = new ArrayList<String>();
				for (int i = logIndex; i >= logIndex-20 && i >= 0; i--) {
					envResults.add(logs[i]);
					if (logs[i].equals("HOME analyzing environment logs: " + "CLOCK=09:00@HOME"))
						break;
				}
				assertTrue(envResults.contains("WINDOWS_MANAGEMENT responding to TURN_ON=All"));
				break;
				
			case "Automated window close":
				envResults = new ArrayList<String>();
				for (int i = logIndex; i >= logIndex-20 && i >= 0; i--) {
					envResults.add(logs[i]);
					if (logs[i].equals("HOME analyzing environment logs: " + "CLOCK=19:00@HOME"))
						break;
				}
				assertTrue(envResults.contains("WINDOWS_MANAGEMENT responding to TURN_OFF=All"));
				break;
			
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
	    app.home.turnOff();
	}

	private class Interceptor extends PrintStream {
	    public Interceptor(OutputStream out) {
	        super(out, true);
	    }
	    @Override
	    public void println(String s) {
	    	logIndex++;
	        logs[logIndex] = s;
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

			}
		}
		
		@Override
		public void run() {
			super.run();
		}
	}

	
	
	
	//WINDOWS
	
	@Given("Manual window open")
	public void manual_window_open() {
		p.publish("MANUAL_WINDOWS", "TURN_ON=All@MANUAL_WINDOWS");
	}

	@Then("Manual window close")
	public void manual_window_close() {
		p.publish("MANUAL_WINDOWS", "TURN_OFF=All@MANUAL_WINDOWS");
	}
	
	@Given("Automated window open")
	public void automated_window_open() {
		p.publish("HOME", "ENV:CLOCK=09:00@HOME");
	}

	@Then("Automated window  close")
	public void automated_window_close() {
		p.publish("HOME", "ENV:CLOCK=19:00@HOME");
	}
	
	//UI
	
	@Given("Touchscreen input")
	public void touchscreen_input() {
		p.publish("TOUCH_SCREEN", "TOUCH=True@TOUCH_SCREEN");
	}
	
	private List<String> getLast20() {
		List<String> results = new ArrayList<String>();
		for (int i = logIndex; i > logIndex - 20 && i >= 0; i--) {
			results.add(logs[i]);
		}
		return results;
	}

	@When("Touchscreen output")
	public void touchscreen_output() {
		assertTrue(getLast20().contains("TOUCH_SCREEN responding to TOUCH=True"));
	}
	
	@Given("Internet input")
	public void internet_input() {
		p.publish("INTERNET", "INTERNET=Input@INTERNET");
	}

	@When("Internet output")
	public void internet_output() {
		p.publish("INTERNET", "INTERNET=Output@INTERNET");
	}
	
	@Given("Send private input message")
	public void send_private_input_message() {
		assertTrue(getLast20().contains("Send private input message to internet."));
	}

	@Then("Send private output message")
	public void send_private_output_message() {
		assertTrue(getLast20().contains("Private output."));
	}
	
	@Given("Encrypt input with RSA")
	public void encrypt_input_with_RSA() {
		assertTrue(getLast20().contains("Encrypt input with RSA."));
	}
	
	@Then("Decrypt input with RSA")
	public void decrypt_input_with_RSA() {
		//TODO
	}
	
	@Given("Encrypt output with RSA")
	public void encrypt_output_with_RSA() {
		//TODO
	}
	
	@When("Decrypt output with RSA")
	public void decrypt_output_with_RSA() {
		assertTrue(getLast20().contains("Decrypt output with RSA."));
	}

}
