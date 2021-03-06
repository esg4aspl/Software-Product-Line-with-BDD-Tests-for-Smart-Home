package smarthome;

import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Map;

import application.App;
import business.*;
import io.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * TODO description
 */
public class Home extends AbstractSystem {
	
	private List<Rule> rules;
	private Map<Channel, List<Rule>> ruleMap;
	private List<Code> logs;
	private boolean started;
	private boolean turnedOff;
	
	public boolean isStarted() {
		return started;
	}
	
	public boolean isTurnedOff() {
		return turnedOff;
	}
	
	public Home() {
		super();
		rules = new ArrayList<Rule>();
		RuleReader ruleReader = new RuleReader();
		ruleMap = ruleReader.getMap();
		started = false;
		turnedOff = false;
	}
	
	public Channel getChannel() {
		return Channel.HOME;
	}
	
	public void init() throws Exception {
		if (started || turnedOff)
			throw new Exception();
		output("STARTED HOME");
		for (ISystem s : subsystems) {
			if (s instanceof UI) {
				((UI) s).init();
			}
		}
		started = true;
	}
	
	public void turnOff() throws Exception {
		if (!started || turnedOff)
			throw new Exception();
//		output("STOPPED HOME");
		started = false;
		turnedOff = true;
	}
	
	public void respondToEnvironment(String environmentLog) {
		if (!started || turnedOff)
			return;
		output(getChannel() + " analyzing environment logs: " + environmentLog);
		String[] logStrings = environmentLog.split("&");
		logs = new ArrayList<Code>();
		for (String logString : logStrings) {
			logs.add((new Command(logString).getCode()));
		}
		
		for (Rule r : rules) {
			if (r.match(logs)) {
				
				publisher.publish(r.getOutputCommand().toString());
			}
		}
		
	}
	
	public void authenticate(String device) {
		//Do Nothing
	}
	
	//Overridden Subscriber Methods
	@Override
	protected ISubscriber createSubscriber() {
		return new Subscriber();
	}
	
	protected class Subscriber extends Thread implements ISubscriber {
		private boolean alive = true;
		
		
		Jedis jSubscriber = null;
		
		public Subscriber() {
			super();
			jSubscriber = App.getJedis();
		}
		
		@Override
		public void run() {
			if (!alive)
				return;
			try {
				jSubscriber.subscribe(new JedisPubSub() {
				    @Override
				    public void onMessage(String channel, String message) {
				    	if (!alive) {
				    		unsubscribe();
				    		return;
				    	}
				    	
				    	if (message.substring(0, 3).equals("ENV")) {
				    		if (started && alive)
				    			respondToEnvironment(message.substring(4));
				    	} else {
				    		try {
				    			respond(new Command(message));
				    		} catch (Exception e) {
				    			//assertTrue(false);
				    		}
				    	}
				    }
				}, getChannel().toString());
			} finally {
				if (jSubscriber != null)
					jSubscriber.close();
			}
		}
		
		public void kill() {
			alive = false;
			turnedOff = true;
			publisher.publish("ANYTHING=ANYTHING@" + "HOME");
		}
	}
	
}