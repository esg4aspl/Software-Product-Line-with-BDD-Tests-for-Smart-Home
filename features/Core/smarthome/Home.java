package smarthome;

import java.util.ArrayList;
import java.util.List;

import application.App;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * TODO description
 */
public class Home extends AbstractSystem {
	
	Publisher p;
	
	public Home() {
		super();
		
		Subscriber s = new Subscriber();
		s.start();
		p = new Publisher();
		p.start();
	}
	
	@Override
	public void respond(String code) {
		// TODO Auto-generated method stub
		System.out.println("Home responding to " + code);
		
		if (code.equals("Glass break")) {
			p.publish("Alarm", code);
		}
	}

	@Override
	public List<String> render() {
		// TODO Auto-generated method stub
		return null;
	}
	
	class Subscriber extends Thread {
		@Override
		public void run() {
			super.run();
			
			Jedis jSubscriber = null;
			try {
				jSubscriber = App.getJedis();
				jSubscriber.subscribe(new JedisPubSub() {
				    @Override
				    public void onMessage(String channel, String message) {
				    	respond(message);
				    }
				}, "Home");
			} finally {
				if (jSubscriber != null)
					jSubscriber.close();
			}
			
		}
	}
	
	class Publisher extends Thread {
		
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
}