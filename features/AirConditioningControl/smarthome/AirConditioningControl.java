package smarthome;

import java.util.List;

import application.App;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * TODO description
 */
public class AirConditioningControl extends AbstractSystem {
	
	public AirConditioningControl(ISystem parentSystem) {
		super(parentSystem);
		
		Subscriber s = new Subscriber();
		s.start();
	}
	
	@Override
	public void respond(String code) {
		// TODO Auto-generated method stub
		System.out.println("AirConditioningControl responding to " + code);
		parentSystem.respond(code);
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
				}, "AirConditioningControl");
			} finally {
				if (jSubscriber != null)
					jSubscriber.close();
			}
			
		}
	}

}