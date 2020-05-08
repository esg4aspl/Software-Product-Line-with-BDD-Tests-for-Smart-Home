package smarthome;

import java.util.ArrayList;
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
	
	public Home() {
		super();
		rules = new ArrayList<Rule>();
		RuleReader ruleReader = new RuleReader();
		ruleMap = ruleReader.getMap();
	}
	
	public Channel getChannel() {
		return Channel.HOME;
	}
	
	public void init() {
		for (ISystem s : subsystems) {
			if (s instanceof UI) {
				((UI) s).init();
			}
		}
	}
	
	public void respondToEnvironment(String environmentLog) {
		System.out.println("\n" + getChannel() + " analyzing environment logs: " + environmentLog);
		String[] logStrings = environmentLog.split("&");
		List<Code> logs = new ArrayList<Code>();
		for (String logString : logStrings) {
			logs.add((new Command(logString).getCode()));
		}
		
		for (Rule r : rules) {
			if (r.match(logs)) {
				publisher.publish(r.getOutputCommand().toString());
			}
		}
		
	}
	
	
	
	
	
	
	
	
	//Overridden Subscriber Methods
	@Override
	protected ISubscriber createSubscriber() {
		return new Subscriber();
	}
	
	protected class Subscriber extends Thread implements ISubscriber {
		@Override
		public void run() {
			super.run();
			
			Jedis jSubscriber = null;
			try {
				jSubscriber = App.getJedis();
				jSubscriber.subscribe(new JedisPubSub() {
				    @Override
				    public void onMessage(String channel, String message) {
				    	if (message.substring(0, 3).equals("ENV")) {
				    		respondToEnvironment(message.substring(4));
				    	} else {
				    		respond(new Command(message));
				    	}
				    }
				}, getChannel().toString());
			} finally {
				if (jSubscriber != null)
					jSubscriber.close();
			}
			
		}
	}
	
}