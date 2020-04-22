package business;

import java.util.List;

import application.App;
import java.util.ArrayList;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * TODO description
 */
public abstract class AbstractSystem implements ISystem {
	
	protected List<ISystem> subsystems;
	protected ISystem parentSystem;
	protected Subscriber subscriber;
	protected Publisher publisher;
	
	//CONSTRUCTORS
	public AbstractSystem() {
		this(null);
	}

	public AbstractSystem(ISystem parentSystem) {
		this.parentSystem = parentSystem;
		this.subsystems = new ArrayList<ISystem>();
		subscriber = new Subscriber();
		publisher = new Publisher();
		
		subscriber.start();
		publisher.start();
	}
	
	//ABSTRACT METHODS
	public abstract List<String> render();
	public abstract Channel getChannel();
	
	public void respond(Code code) {
		System.out.println(getChannel() + " responding to " + code);
    	if (parentSystem != null)
    		parentSystem.respond(code);
	}
	
	//GETTERS
	public List<ISystem> getSubsystems() {
		return subsystems;
	}
	
	public ISystem getParentSystem() {
		return parentSystem;
	}
	
	
	//PUB-SUB THREADS
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
				    	respond(Code.parseCode(message));
				    }
				}, getChannel().toString());
			} finally {
				if (jSubscriber != null)
					jSubscriber.close();
			}
			
		}
	}
	
	class Publisher extends Thread {
		
		public void publish(Channel channel, String message) {
			Jedis jPublisher = null;
			try {
				jPublisher = App.getJedis();
				jPublisher.publish(channel.toString(), message);
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