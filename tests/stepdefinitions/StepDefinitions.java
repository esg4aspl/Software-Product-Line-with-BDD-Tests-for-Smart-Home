package stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import application.App;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import redis.clients.jedis.Jedis;

public class StepDefinitions {
	
	//Core
	@Then("turn on")
	public void turn_on() {
	    app.home.init();
	}
	
	@Then("turn off")
	public void turn_off() {
	    app.home.turnOff();
	}

	
	@When("output to console")
	public void output_to_console() {
		switch(previousStep()) {
			//Core
			case "turn on": assertEquals("STARTED HOME", getLastLog()); break;
			case "turn off": assertEquals("STOPPED HOME", getLastLog()); break;
			//WindowsManagement
			case "open windows manual": assertTrue(getLast20().contains("WINDOWS_MANAGEMENT responding to TURN_ON=All")); break;
			case "close windows manual": assertTrue(getLast20().contains("WINDOWS_MANAGEMENT responding to TURN_OFF=All")); break;
			case "open windows automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=09:00@HOME").contains("WINDOWS_MANAGEMENT responding to TURN_ON=All")); break;
			case "close windows automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=19:00@HOME").contains("WINDOWS_MANAGEMENT responding to TURN_OFF=All")); break;
			//BlindsManagement
			case "open blinds manual": assertTrue(getLast20().contains("BLINDS_MANAGEMENT responding to TURN_ON=All")); break;
			case "close blinds manual": assertTrue(getLast20().contains("BLINDS_MANAGEMENT responding to TURN_OFF=All")); break;
			case "open blinds automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=09:00@HOME").contains("BLINDS_MANAGEMENT responding to TURN_ON=All")); break;
			case "close blinds automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=19:00@HOME").contains("BLINDS_MANAGEMENT responding to TURN_OFF=All")); break;
			//LightManagement
			case "turn on light manual": assertTrue(getLast20().contains("LIGHT_MANAGEMENT responding to TURN_ON=All")); break;
			case "turn off light manual": assertTrue(getLast20().contains("LIGHT_MANAGEMENT responding to TURN_OFF=All")); break;
			case "turn on inhouse light automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=07:00@HOME").contains("LIGHT_MANAGEMENT responding to TURN_ON=All Inhouse")); break;
			case "turn off inhouse light automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=23:00@HOME").contains("LIGHT_MANAGEMENT responding to TURN_OFF=All Inhouse")); break;
			case "turn on perimeter light automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=21:00@HOME").contains("LIGHT_MANAGEMENT responding to TURN_ON=All Perimeter")); break;
			case "turn off perimeter light automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=09:00@HOME").contains("LIGHT_MANAGEMENT responding to TURN_OFF=All Perimeter")); break;
			//AVManagement
			case "start AV automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=10:00@HOME").contains("AV_MANAGEMENT responding to TURN_ON=Start")); break;
			case "stop AV automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=19:00@HOME").contains("AV_MANAGEMENT responding to TURN_OFF=Stop")); break;
			case "turn on AV automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=07:00@HOME").contains("AV_MANAGEMENT responding to TURN_ON=Turn On")); break;
			case "turn off AV automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=22:00@HOME").contains("AV_MANAGEMENT responding to TURN_OFF=Turn Off")); break;
			//MoodsManagement
			case "brighten moods automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=16:00@HOME").contains("MOODS_MANAGEMENT responding to TURN_ON=Brighten")); break;
			case "dim moods automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=20:00@HOME").contains("MOODS_MANAGEMENT responding to TURN_ON=Dim")); break;
			case "turn on moods automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=07:00@HOME").contains("MOODS_MANAGEMENT responding to TURN_ON=Turn On")); break;
			case "turn off moods automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=22:00@HOME").contains("MOODS_MANAGEMENT responding to TURN_OFF=Turn Off")); break;
			//IrrigationSprinklers
			case "turn on irrigation sprinklers manual": assertTrue(getLast20().contains("IRRIGATION_SPRINKLERS responding to TURN_ON=All")); break;
			case "turn off irrigation sprinklers manual": assertTrue(getLast20().contains("IRRIGATION_SPRINKLERS responding to TURN_OFF=All")); break;
			case "turn on irrigation sprinklers automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=14:00@HOME").contains("IRRIGATION_SPRINKLERS responding to TURN_ON=All")); break;
			case "turn off irrigation sprinklers automatic": assertTrue(getLast20("Home analyzing environment logs: CLOCK=15:00@HOME").contains("IRRIGATION_SPRINKLERS responding to TURN_OFF=All")); break;
			//UI
			case "output via internet": assertTrue(getLast20().contains("UI responding to INTERNET=Input")); break;
			//FireControl
			case "call fire department": assertTrue(getLast20().contains("FIRST_AID_GROUP calls Fire Department.")); break;
			case "call other group for fire": assertTrue(getLast20().contains("FIRST_AID_GROUP calls Other Group.")); break;
			case "open fire sprinklers": assertTrue(getLast20().contains("FIRE_SPRINKLERS responding to TURN_ON=All")); break;
			case "close fire sprinklers": assertTrue(getLast20().contains("FIRE_SPRINKLERS responding to TURN_OFF=All")); break;
			//Alarm
			case "turn on bell": assertTrue(getLast20("Home analyzing environment logs: CLOCK=07:00@HOME").contains("Bell responding to TURN_ON=Morning Alarms")); break;
			case "turn off bell": assertTrue(getLast20("Home analyzing environment logs: CLOCK=07:01@HOME").contains("Bell responding to TURN_OFF=Morning Alarms")); break;
			case "turn on lights": assertTrue(getLast20("Home analyzing environment logs: CLOCK=07:00@HOME").contains("Lights responding to TURN_ON=Morning Alarms")); break;
			case "turn off lights": assertTrue(getLast20("Home analyzing environment logs: CLOCK=07:01@HOME").contains("Lights responding to TURN_OFF=Morning Alarms")); break;
			case "turn on siren": assertTrue(getLast20("Home analyzing environment logs: CLOCK=07:00@HOME").contains("Siren responding to TURN_ON=Morning Alarms")); break;
			case "turn off siren": assertTrue(getLast20("Home analyzing environment logs: CLOCK=07:01@HOME").contains("Siren responding to TURN_OFF=Morning Alarms")); break;
		}
	}
	
	//Windows Management
	@Given("close windows manual")
	public void close_windows_manual() { p.publish("MANUAL_WINDOWS", "TURN_OFF=All@MANUAL_WINDOWS"); }
	@Then("open windows manual")
	public void open_windows_manual() { p.publish("MANUAL_WINDOWS", "TURN_ON=All@MANUAL_WINDOWS"); }
	@Given("open windows automatic")
	public void open_windows_automatic() { p.publish("HOME", "ENV:CLOCK=09:00@HOME"); }
	@Then("close windows automatic")
	public void close_windows_automatic() { p.publish("HOME", "ENV:CLOCK=19:00@HOME"); }
	
	//BlindsManagement
	@Given("close blinds manual")
	public void close_blinds_manual() { p.publish("MANUAL_BLINDS", "TURN_OFF=All@MANUAL_BLINDS"); }
	@Then("open blinds manual")
	public void open_blinds_manual() { p.publish("MANUAL_BLINDS", "TURN_ON=All@MANUAL_BLINDS"); }
	@Given("open blinds automatic")
	public void open_blinds_automatic() { p.publish("HOME", "ENV:CLOCK=09:00@HOME"); }
	@Then("close blinds automatic")
	public void close_blinds_automatic() { p.publish("HOME", "ENV:CLOCK=19:00@HOME"); }
	
	//LightManagement
	@Given("turn on light manual")
	public void turn_on_light_manual() { p.publish("MANUAL_ILLUMINATION", "TURN_ON=All@MANUAL_ILLUMINATION"); }
	@Then("turn off light manual")
	public void turn_off_light_manual() { p.publish("MANUAL_ILLUMINATION", "TURN_OFF=All@MANUAL_ILLUMINATION"); }
	@Given("turn on inhouse light automatic")
	public void turn_on_inhouse_light_automatic() { p.publish("HOME", "ENV:CLOCK=07:00@HOME"); }
	@Then("turn off inhouse light automatic")
	public void turn_off_inhouse_light_automatic() { p.publish("HOME", "ENV:CLOCK=23:00@HOME"); }
	@Given("turn on perimeter light automatic")
	public void turn_on_perimeter_light_automatic() { p.publish("HOME", "ENV:CLOCK=21:00@HOME"); }
	@Then("turn off perimeter light automatic")
	public void turn_off_perimeter_light_automatic() { p.publish("HOME", "ENV:CLOCK=09:00@HOME"); }
	
	//AVManagement
	@Given("start AV automated")
	public void start_AV_automated() { p.publish("HOME", "ENV:CLOCK=10:00@HOME"); }
	@Then("stop AV automated")
	public void stop_AV_automated() { p.publish("HOME", "ENV:CLOCK=19:00@HOME"); }
	@Given("turn on AV automated")
	public void turn_on_AV_automated() { p.publish("HOME", "ENV:CLOCK=07:00@HOME"); }
	@Then("turn off AV automated")
	public void turn_off_AV_automated() { p.publish("HOME", "ENV:CLOCK=22:00@HOME"); }
	
	//MoodsManagement
	@Then("brighten moods automatic")
	public void brighten_moods_automatic() { p.publish("HOME", "ENV:CLOCK=16:00@HOME"); }
	@Given("dim moods automatic")
	public void dim_moods_automatic() { p.publish("HOME", "ENV:CLOCK=20:00@HOME"); }
	@Given("turn on moods automatic")
	public void turn_on_moods_automatic() { p.publish("HOME", "ENV:CLOCK=07:00@HOME"); }
	@Then("turn off moods automatic")
	public void turn_off_moods_automatic() { p.publish("HOME", "ENV:CLOCK=22:00@HOME"); }
	
	//IrrigationSprinklers
	@Then("turn on irrigation sprinklers manual")
	public void turn_on_irrigation_sprinklers_manual() { p.publish("MANUAL_SPRINKLERS", "TURN_ON=All@MANUAL_SPRINKLERS");  }
	@Given("turn off irrigation sprinklers manual")
	public void turn_off_irrigation_sprinklers_manual() { p.publish("MANUAL_SPRINKLERS", "TURN_OFF=All@MANUAL_SPRINKLERS");  }
	@Given("turn on irrigation sprinklers automatic")
	public void turn_on_irrigation_sprinklers_automatic() { p.publish("HOME", "ENV:CLOCK=14:00@HOME"); }
	@Then("turn off irrigation sprinklers automatic")
	public void turn_off_irrigation_sprinklers_automatic() { p.publish("HOME", "ENV:CLOCK=15:00@HOME"); }
	
	//UI
	@Given("input via touchscreen")
	public void input_via_touchscreen() { p.publish("TOUCH_SCREEN", "TOUCH=True@TOUCH_SCREEN"); }
	@When("output via touchscreen")
	public void output_via_touchscreen() { assertTrue(getLast20().contains("TOUCH_SCREEN responding to TOUCH=True")); }
	@Given("input via Internet")
	public void input_via_Internet() { p.publish("INTERNET", "INTERNET=Input@INTERNET"); }
	@When("output via Internet")
	public void output_via_Internet() { assertTrue(getLast20().contains("INTERNET creates response.")); }
	@Given("send RSA encrypted input message")
	public void send_RSA_encrypted_input_message() { p.publish("INTERNET", "INTERNET=Input@INTERNET"); }
	@Then("send RSA encrypted output message")
	public void send_RSA_encrypted_output_message() { assertTrue(getLast20().contains("INTERNET creates RSA encrypted response.")); }
	@Given("send DES encrypted input message")
	public void send_DES_encrypted_input_message() { p.publish("INTERNET", "INTERNET=Input@INTERNET");  }
	@Then("send DES encrypted output message")
	public void send_DES_encrypted_output_message() { assertTrue(getLast20().contains("INTERNET creates DES encrypted response.")); }
	
	//FireControl
	@Given("call fire department")
	public void call_fire_department() { p.publish("HOME", "ENV:FIRE=On@HOME"); }
	@When("call other group for fire")
	public void call_other_group_for_fire() { p.publish("HOME", "ENV:FIRE=On@HOME"); }
	@Given("open fire sprinklers")
	public void open_fire_sprinklers() { p.publish("HOME", "ENV:FIRE=On@HOME"); }
	@Then("close fire sprinklers")
	public void close_fire_sprinklers() { p.publish("HOME", "ENV:FIRE=Off@HOME"); }
	
	//Alarm
	@Given("turn on bell")
	public void turn_on_bell() { p.publish("HOME", "ENV:CLOCK=07:00@HOME"); }
	@Then("turn off bell")
	public void turn_off_bell() { p.publish("HOME", "ENV:CLOCK=07:01@HOME"); }
	@Then("turn on siren")
	public void turn_on_siren() { p.publish("HOME", "ENV:CLOCK=07:00@HOME"); }
	@Given("turn off siren")
	public void turn_off_siren() { p.publish("HOME", "ENV:CLOCK=07:01@HOME"); }
	@Given("turn on lights")
	public void turn_on_lights() { p.publish("HOME", "ENV:CLOCK=07:00@HOME"); }
	@Then("turn off lights")
	public void turn_off_lights() { p.publish("HOME", "ENV:CLOCK=07:01@HOME"); }
	

	
	
	
	
	
	
	
	
	
	
	
	
	
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

	private List<String> getLast20() {
 		List<String> results = new ArrayList<String>();
 		for (int i = logIndex; i > logIndex - 20 && i >= 0; i--) {
 			results.add(logs[i]);
 		}
 		return results;
 	}
	
	private List<String> getLast20(String stopCondition) {
 		List<String> results = new ArrayList<String>();
 		for (int i = logIndex; i > logIndex - 20 && i >= 0; i--) {
 			results.add(logs[i]);
 			if (logs[i].equals(stopCondition))
 				break;
 		}
 		return results;
 	}

}
