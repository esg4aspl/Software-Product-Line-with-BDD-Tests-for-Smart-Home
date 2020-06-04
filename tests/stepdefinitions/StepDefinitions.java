package stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import application.App;
import business.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import redis.clients.jedis.Jedis;
import smarthome.Internet;
import smarthome.UI;

public class StepDefinitions {
	
	
	
	//Core
	@Then("turn on")
	public void turn_on() {
		try {			
			app.home.init();
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Then("turn off")
	public void turn_off() {
		assertTrue(outputBag.isEmpty());	
	    try {
			app.home.turnOff();
		} catch (Exception e) {
			fail();
		}
	    
	}
	
	private void fail() {
		assertTrue(false);
	}

	private List<String> readOutputs() {
		List<String> outputs = outputBag.clearOutputs();
		for (String s : outputs) {
			homeOutputs.add(s);
		}
		this.lastOutputBagSize = 0;
		return outputs;
	}
	
	private void readUIMessages() {
		List<String> messages = uiBag.clearMessages();
		for (String s : messages) {
			uiMessages.add(s);
		}
	}
	
	private int readInternetMessages() {
		List<String> messages = internetBag.clearMessages();
		for (String s : messages) {
			internetMessages.add(s);
		}
		return messages.size();
	}
	
	private int readEncryptedInternetMessages() {
		List<String> messages = encryptedInternetBag.clearMessages();
		for (String s : messages) {
			encryptedInternetMessages.add(s);
		}
		return messages.size();
	}
	
	@When("output to console")
	public void output_to_console() {
		
		List<String> newOutputs = readOutputs();
		
		boolean found = false;
		
		switch(previousStep()) {
			//FCES
			case "[": fail(); found = true; break;
			case "output to console": fail(); found = true; break;
			
			//Core
			case "turn on": assertEquals("STARTED HOME", getLastOutput()); found = true; break;
			case "turn off": assertEquals("STOPPED HOME", getLastOutput()); fail(); found = true; break;
			//WindowsManagement
			case "open windows manual": assertTrue(getLast20().contains("WINDOWS_MANAGEMENT responding to TURN_ON=All")); found = true; break;
			case "close windows manual": assertTrue(getLast20().contains("WINDOWS_MANAGEMENT responding to TURN_OFF=All")); found = true; break;
			case "open windows automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=09:00@HOME").contains("WINDOWS_MANAGEMENT responding to TURN_ON=All")); found = true; break;
			case "close windows automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=19:00@HOME").contains("WINDOWS_MANAGEMENT responding to TURN_OFF=All")); found = true; break;
			//BlindsManagement
			case "open blinds manual": assertTrue(getLast20().contains("BLINDS_MANAGEMENT responding to TURN_ON=All")); found = true; break;
			case "close blinds manual": assertTrue(getLast20().contains("BLINDS_MANAGEMENT responding to TURN_OFF=All")); found = true; break;
			case "open blinds automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=09:00@HOME").contains("BLINDS_MANAGEMENT responding to TURN_ON=All")); found = true; break;
			case "close blinds automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=19:00@HOME").contains("BLINDS_MANAGEMENT responding to TURN_OFF=All")); found = true; break;
			//LightManagement
			case "turn on light manual": assertTrue(getLast20().contains("LIGHT_MANAGEMENT responding to TURN_ON=All")); found = true; break;
			case "turn off light manual": assertTrue(getLast20().contains("LIGHT_MANAGEMENT responding to TURN_OFF=All")); found = true; break;
			case "turn on inhouse light automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=07:00@HOME").contains("LIGHT_MANAGEMENT responding to TURN_ON=All Inhouse")); found = true; break;
			case "turn off inhouse light automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=23:00@HOME").contains("LIGHT_MANAGEMENT responding to TURN_OFF=All Inhouse")); found = true; break;
			case "turn on perimeter light automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=21:00@HOME").contains("LIGHT_MANAGEMENT responding to TURN_ON=All Perimeter")); found = true; break;
			case "turn off perimeter light automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=09:00@HOME").contains("LIGHT_MANAGEMENT responding to TURN_OFF=All Perimeter")); found = true; break;
			//AVManagement
			case "start AV automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=10:00@HOME").contains("AV_MANAGEMENT responding to TURN_ON=Start")); found = true; break;
			case "stop AV automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=19:00@HOME").contains("AV_MANAGEMENT responding to TURN_OFF=Stop")); found = true; break;
			case "turn on AV automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=07:00@HOME").contains("AV_MANAGEMENT responding to TURN_ON=Turn On")); found = true; break;
			case "turn off AV automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=22:00@HOME").contains("AV_MANAGEMENT responding to TURN_OFF=Turn Off")); found = true; break;
			//MoodsManagement
			case "brighten moods automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=16:00@HOME").contains("MOODS_MANAGEMENT responding to TURN_ON=Brighten")); found = true; break;
			case "dim moods automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=20:00@HOME").contains("MOODS_MANAGEMENT responding to TURN_ON=Dim")); found = true; break;
			case "turn on moods automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=07:00@HOME").contains("MOODS_MANAGEMENT responding to TURN_ON=Turn On")); found = true; break;
			case "turn off moods automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=22:00@HOME").contains("MOODS_MANAGEMENT responding to TURN_OFF=Turn Off")); found = true; break;
			//IrrigationSprinklers
			case "turn on irrigation sprinklers manual": assertTrue(getLast20().contains("IRRIGATION_SPRINKLERS responding to TURN_ON=All")); found = true; break;
			case "turn off irrigation sprinklers manual": assertTrue(getLast20().contains("IRRIGATION_SPRINKLERS responding to TURN_OFF=All")); found = true; break;
			case "turn on irrigation sprinklers automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=14:00@HOME").contains("IRRIGATION_SPRINKLERS responding to TURN_ON=All")); found = true; break;
			case "turn off irrigation sprinklers automatic": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=15:00@HOME").contains("IRRIGATION_SPRINKLERS responding to TURN_OFF=All")); found = true; break;
			//FireControl
//			case "call fire department": assertTrue(getLast20().contains("FIRST_AID_GROUP calls Fire Department.")); found = true; break;
			case "call other group for fire": assertTrue(getLast20().contains("FIRST_AID_GROUP calls Other Group.")); found = true; break;
			case "open fire sprinklers": assertTrue(getLast20().contains("FIRE_SPRINKLERS responding to TURN_ON=All")); found = true; break;
			case "close fire sprinklers": assertTrue(getLast20().contains("FIRE_SPRINKLERS responding to TURN_OFF=All")); found = true; break;
			
			//Security
			case "use keypad for authentication": assertTrue(getLast20().contains("Authenticated with keypad!")); found = true; break;
			case "use retina scanner for authentication": assertTrue(getLast20().contains("Authenticated with retina scanner!")); found = true; break;
			case "use fingerprint reader for authentication": assertTrue(getLast20().contains("Authenticated with fingerprint reader!")); found = true; break;
			
			//Alarm
			case "turn on bell": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=07:00@HOME").contains("Bell responding to TURN_ON=Morning Alarms")); found = true; break;
			case "turn off bell": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=07:01@HOME").contains("Bell responding to TURN_OFF=Morning Alarms")); found = true; break;
			case "turn on lights": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=07:00@HOME").contains("Lights responding to TURN_ON=Morning Alarms")); found = true; break;
			case "turn off lights": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=07:01@HOME").contains("Lights responding to TURN_OFF=Morning Alarms")); found = true; break;
			case "turn on siren": assertTrue(getLast20("HOME analyzing environment logs: CLOCK=07:00@HOME").contains("Siren responding to TURN_ON=Morning Alarms")); found = true; break;
			case "turn off siren": 
				if (!this.consoleOutputIsAbout.equals("fire")) {
					assertTrue(getLast20("HOME analyzing environment logs: CLOCK=07:01@HOME").contains("Siren responding to TURN_OFF=Morning Alarms")); found = true; break;
				}
		}
		
		if (!found) {
			switch(consoleOutputIsAbout) {
				case "input via touchscreen": assertTrue(uiBag.isEmpty()); assertTrue(getLast20().contains("TOUCH_SCREEN responding to TOUCH=True")); found = true; break;
				case "input via internet": 
					assertTrue(internetBag.isEmpty()); 
					assertTrue(internetMessages.size() == 0); 
					assertTrue(getLast20().contains("INTERNET responding to input."));
					found = true; break;
				case "send encrypted input message":
					assertTrue(internetBag.isEmpty()); 
					assertTrue(encryptedInternetBag.isEmpty()); 
					assertTrue(internetMessages.size() == 0); 
					assertTrue(getLast20().contains("INTERNET responding to input."));
					found = true; break;
				case "fire":
					assertTrue(!newOutputs.contains("Siren responding to TURN_ON=Fire"));
					assertTrue(!newOutputs.contains("Siren responding to TURN_OFF=Fire"));
					
					found = true; break;
					
			}
		}
		
		this.consoleOutputIsAbout = "";
	}
	
	//Windows Management
	@Given("close windows manual")
	public void close_windows_manual() { assertTrue(outputBag.isEmpty()); p.publish("MANUAL_WINDOWS", "TURN_OFF=All@MANUAL_WINDOWS"); }
	@Then("open windows manual")
	public void open_windows_manual() { assertTrue(outputBag.isEmpty()); p.publish("MANUAL_WINDOWS", "TURN_ON=All@MANUAL_WINDOWS");  }
	@Given("open windows automatic")
	public void open_windows_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=09:00@HOME");  }
	@Then("close windows automatic")
	public void close_windows_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=19:00@HOME");  }
	
	//BlindsManagement
	@Given("close blinds manual")
	public void close_blinds_manual() { assertTrue(outputBag.isEmpty()); p.publish("MANUAL_BLINDS", "TURN_OFF=All@MANUAL_BLINDS"); }
	@Then("open blinds manual")
	public void open_blinds_manual() { assertTrue(outputBag.isEmpty()); p.publish("MANUAL_BLINDS", "TURN_ON=All@MANUAL_BLINDS");  }
	@Given("open blinds automatic")
	public void open_blinds_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=09:00@HOME");  }
	@Then("close blinds automatic")
	public void close_blinds_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=19:00@HOME");  }
	
	//LightManagement
	@Given("turn on light manual")
	public void turn_on_light_manual() { assertTrue(outputBag.isEmpty()); p.publish("MANUAL_ILLUMINATION", "TURN_ON=All@MANUAL_ILLUMINATION");  }
	@Then("turn off light manual")
	public void turn_off_light_manual() { assertTrue(outputBag.isEmpty()); p.publish("MANUAL_ILLUMINATION", "TURN_OFF=All@MANUAL_ILLUMINATION");  }
	@Given("turn on inhouse light automatic")
	public void turn_on_inhouse_light_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=07:00@HOME");  }
	@Then("turn off inhouse light automatic")
	public void turn_off_inhouse_light_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=23:00@HOME");  }
	@Given("turn on perimeter light automatic")
	public void turn_on_perimeter_light_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=21:00@HOME");  }
	@Then("turn off perimeter light automatic")
	public void turn_off_perimeter_light_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=09:00@HOME");  }
	
	//AVManagement
	@Given("start AV automated")
	public void start_AV_automated() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=10:00@HOME");  }
	@Then("stop AV automated")
	public void stop_AV_automated() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=19:00@HOME");  }
	@Given("turn on AV automated")
	public void turn_on_AV_automated() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=07:00@HOME");  }
	@Then("turn off AV automated")
	public void turn_off_AV_automated() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=22:00@HOME");  }
	
	//MoodsManagement
	@Then("brighten moods automatic")
	public void brighten_moods_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=16:00@HOME");  }
	@Given("dim moods automatic")
	public void dim_moods_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=20:00@HOME");  }
	@Given("turn on moods automatic")
	public void turn_on_moods_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=07:00@HOME");  }
	@Then("turn off moods automatic")
	public void turn_off_moods_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=22:00@HOME");  }
	
	//IrrigationSprinklers
	@Then("turn on irrigation sprinklers manual")
	public void turn_on_irrigation_sprinklers_manual() { assertTrue(outputBag.isEmpty()); p.publish("MANUAL_SPRINKLERS", "TURN_ON=All@MANUAL_SPRINKLERS");   }
	@Given("turn off irrigation sprinklers manual")
	public void turn_off_irrigation_sprinklers_manual() { assertTrue(outputBag.isEmpty()); p.publish("MANUAL_SPRINKLERS", "TURN_OFF=All@MANUAL_SPRINKLERS");   }
	@Given("turn on irrigation sprinklers automatic")
	public void turn_on_irrigation_sprinklers_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=14:00@HOME");   }
	@Then("turn off irrigation sprinklers automatic")
	public void turn_off_irrigation_sprinklers_automatic() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=15:00@HOME");   }
	
	//UI
	@Given("input via touchscreen")
	public void input_via_touchscreen() { assertTrue(outputBag.isEmpty()); consoleOutputIsAbout = "input via touchscreen"; p.publish("TOUCH_SCREEN", "TOUCH=True@TOUCH_SCREEN"); }
	@When("output via touchscreen")
	public void output_via_touchscreen() { readUIMessages(); assertTrue(getLastUIMessage().equals("TOUCH_SCREEN responding to TOUCH=True")); }
	
	@Given("input via Internet")
	public void input_via_Internet() { assertTrue(outputBag.isEmpty()); consoleOutputIsAbout = "input via internet"; p.publish("INTERNET", "INTERNET=Input@INTERNET"); }
	@When("output via Internet")
	public void output_via_Internet() { assertTrue(readInternetMessages() > 0); assertTrue(getLastInternetMessage().equals("INTERNET completed transfer.")); }
	
	@Given("send RSA encrypted input message")
	public void send_RSA_encrypted_input_message() {
		consoleOutputIsAbout = "send encrypted input message";
		p.publish("INTERNET", "INTERNET=EncryptedInput@INTERNET");
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertFalse(internetBag.getMessages().get(internetBag.getMessages().size()-1).contains("error"));
	}
	@Then("send RSA encrypted output message")
	public void send_RSA_encrypted_output_message() {
		assertTrue(internetBag.isEmpty());
		assertTrue(readEncryptedInternetMessages() > 0); assertTrue(getLastEncryptedInternetMessage().equals("INTERNET creates RSA encrypted response.")); 
	}
	
	@Given("send DES encrypted input message")
	public void send_DES_encrypted_input_message() { 
		consoleOutputIsAbout = "send encrypted input message";
		p.publish("INTERNET", "INTERNET=EncryptedInput@INTERNET");
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertFalse(internetBag.getMessages().get(internetBag.getMessages().size()-1).contains("error"));
	}
	@Then("send DES encrypted output message")
	public void send_DES_encrypted_output_message() { 
		assertTrue(internetBag.isEmpty());
		assertTrue(readEncryptedInternetMessages() > 0); assertTrue(getLastEncryptedInternetMessage().equals("INTERNET creates DES encrypted response.")); 
	}
	
	//FireControl
	@Given("call fire department")
	public void call_fire_department() { assertTrue(outputBag.isEmpty()); this.consoleOutputIsAbout = "fire"; p.publish("HOME", "ENV:FIRE=On@HOME"); }
	@When("call other group for fire")
	public void call_other_group_for_fire() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:FIRE=On@HOME"); }
	@Given("open fire sprinklers")
	public void open_fire_sprinklers() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:FIRE=SprinklersOn@HOME"); }
	@Then("close fire sprinklers")
	public void close_fire_sprinklers() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:FIRE=SprinklersOff@HOME"); }
	@When("five minutes passed")
	public void five_minutes_passed() { 
		assertTrue(
				outputBag.getOutputs().contains("HOME analyzing environment logs: FIRE=On@HOME")
				|| outputBag.getOutputs().contains("HOME analyzing environment logs: GLASSBREAK=Kitchen@HOME")
				); 
		if (outputBag.getOutputs().contains("HOME analyzing environment logs: FIRE=On@HOME")) {
			p.publish("HOME", "ENV:FIRE=FiveMinutes@HOME"); 
		} else if (outputBag.getOutputs().contains("HOME analyzing environment logs: GLASSBREAK=Kitchen@HOME")) {
			assertTrue(outputBag.getOutputs().contains("Bell responding to TURN_ON=Kitchen"));
			p.publish("HOME", "ENV:GLASSBREAK=FiveMinutes@HOME"); 
		}
		
	}
	
	private int lastOutputBagSize = 0;
	
	//Alarm
	@Given("turn on bell")
	public void turn_on_bell() { 
		consoleOutputIsAbout = "glassbreak";
		if (consoleOutputIsAbout.equals("glassbreak")) {
			assertTrue(outputBag.getOutputs().size() > this.lastOutputBagSize);
			this.lastOutputBagSize = outputBag.getOutputs().size();
			assertTrue(outputBag.getOutputs().contains("Bell responding to TURN_ON=Kitchen"));
		} else {
			
			assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=07:00@HOME"); 
		}
	}
	@Then("turn off bell")
	public void turn_off_bell() {
		consoleOutputIsAbout = "glassbreak";
		if (consoleOutputIsAbout.equals("glassbreak")) {
			assertTrue(outputBag.getOutputs().size() > this.lastOutputBagSize);
			this.lastOutputBagSize = outputBag.getOutputs().size();
			assertTrue(outputBag.getOutputs().contains("Bell responding to TURN_OFF=Kitchen"));
		} else {
			assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=07:01@HOME"); 
		}
	}
	
	@Then("turn on siren")
	public void turn_on_siren() { 
		consoleOutputIsAbout = "fire";
		if (consoleOutputIsAbout.equals("fire")) {
			List<String> newOutputs = readOutputs();
			assertTrue(!newOutputs.contains("FIRE_SPRINKLERS responding to TURN_ON=All"));
			assertTrue(!newOutputs.contains("FIRE_SPRINKLERS responding to TURN_OFF=All"));
			assertTrue(!newOutputs.contains("FIRST_AID_GROUP calls Other Group."));
			assertTrue(getLast20("HOME analyzing environment logs: FIRE=On@HOME").contains("Siren responding to TURN_ON=Fire"));
		} else {
			assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=07:00@HOME");
		}
	}
	@Given("turn off siren")
	public void turn_off_siren() { 
		consoleOutputIsAbout = "fire";
		if (consoleOutputIsAbout.equals("fire")) {
			readOutputs();
			assertTrue(getLast20("HOME analyzing environment logs: FIRE=FiveMinutes@HOME").contains("Siren responding to TURN_OFF=Fire"));
		} else {
			assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=07:01@HOME"); 
		}
	}
	@Given("turn on lights")
	public void turn_on_lights() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=07:00@HOME"); }
	@Then("turn off lights")
	public void turn_off_lights() { assertTrue(outputBag.isEmpty()); p.publish("HOME", "ENV:CLOCK=07:01@HOME"); }
	
	
	//Security
	
	@When("use keypad for authentication")
	public void use_keypad_for_authentication() {
		assertTrue(outputBag.isEmpty());
	    app.home.authenticate("123456");
	}
	
	@When("use fingerprint reader for authentication")
	public void use_fingerprint_reader_for_authentication() {
		assertTrue(outputBag.isEmpty());
	    app.home.authenticate("gc46tr736t4r6dtux");
	}
	
	@When("use retina scanner for authentication")
	public void use_retina_scanner_for_authentication() {
		assertTrue(outputBag.isEmpty());
	    app.home.authenticate("n 8475yt8374c3b8 34bc384ygcn 3u4ry");
	}
	
	@Then("intrusion detected via glassbreak sensor")
	public void intrusion_detected_via_glassbreak_sensor() {
			
		//this.consoleOutputIsAbout = "intrusion";
		assertTrue(outputBag.isEmpty());
		assertFalse(app.home.isTurnedOff());
		consoleOutputIsAbout = "glassbreak";
		p.publish("HOME", "ENV:GLASSBREAK=Kitchen@HOME");
	
	}

	private OutputBag outputBag;
	private String consoleOutputIsAbout;
	private UIBag uiBag;
	private InternetBag internetBag;
	private EncryptedInternetBag encryptedInternetBag;
	private List<String> homeOutputs;
	private List<String> uiMessages;
	private List<String> internetMessages;
	private List<String> encryptedInternetMessages;
	private String[] steps;
	Publisher p;
	App app;
	int stepCounter;

	private String getLastOutput() {
		if (homeOutputs.size() > 0) {
			return homeOutputs.get(homeOutputs.size()-1);
		} else {
			return "";
		}
	}
	
	
	
	@Before
	public void before(Scenario scenario) {
		consoleOutputIsAbout = "";
	    homeOutputs = new ArrayList<String>();
	    uiMessages = new ArrayList<String>();
	    internetMessages = new ArrayList<String>();
	    encryptedInternetMessages = new ArrayList<String>();
	    outputBag = OutputBag.getInstance();
	    uiBag = UIBag.getInstance();
	    internetBag = InternetBag.getInstance();
	    encryptedInternetBag = EncryptedInternetBag.getInstance();
	    app = new App();
		app.init();
		p = new Publisher();
		stepCounter = -1;
	}
	
	@After
	public void after(Scenario scenario) {
		app.home.kill();
		uiBag.clearMessages();
		outputBag.clearOutputs();
		internetBag.clearMessages();
		encryptedInternetBag.clearMessages();
	}
	
	@AfterStep
	public void afterStep() {
		try {
			Thread.sleep(50);
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
	private String previousStep() {
		if (stepCounter > 0)
			return steps[stepCounter-1];
		return "";
	}
	
	@Given("The environment is set up with {string}")
	public void the_environment_is_set_up_with(String string) {
		steps = string.split(",");
		homeOutputs.clear();
	}

	@When("[")
	public void begin() {
		if (!steps[0].equals("turn on"))
			fail();
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
	
	private String getLastUIMessage() {
		if (uiMessages.size() == 0) {
			return "";
		} else {
			String r = uiMessages.get(uiMessages.size()-1);
			uiMessages.clear();
			return r;
		}
	} 
	
	private String getLastInternetMessage() {
		if (internetMessages.size() == 0) {
			return "";
		} else {
			String r = internetMessages.get(internetMessages.size()-1);
			internetMessages.clear();
			return r;
		}
	} 
	
	private String getLastEncryptedInternetMessage() {
		if (encryptedInternetMessages.size() == 0) {
			return "";
		} else {
			String r = encryptedInternetMessages.get(encryptedInternetMessages.size()-1);
			encryptedInternetMessages.clear();
			return r;
		}
	} 
	
	private List<String> getLast3InternetMessages() {
 		List<String> results = new ArrayList<String>();
 		for (int i = internetMessages.size()-1; i >= homeOutputs.size()-3 && i >= 0; i--) {
 			results.add(internetMessages.get(i));
 		}
 		return results;
	} 

	private List<String> getLast20() {
 		List<String> results = new ArrayList<String>();
 		for (int i = homeOutputs.size()-1; i >= homeOutputs.size()-20 && i >= 0; i--) {
 			results.add(homeOutputs.get(i));
 		}
 		return results;
 	}
	
	private List<String> getLast20(String stopCondition) {
 		List<String> results = new ArrayList<String>();
 		for (int i = homeOutputs.size()-1; i >= homeOutputs.size()-20 && i >= 0; i--) {
 			String op = homeOutputs.get(i);
 			results.add(op);
 			if (op.equals(stopCondition))
 				break;
 		}
 		return results;
 	}

}
