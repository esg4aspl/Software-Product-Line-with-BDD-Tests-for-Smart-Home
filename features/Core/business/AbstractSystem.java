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
	protected List<Command> commands;
	
	//CONSTRUCTORS
	public AbstractSystem() {
		this(null);
	}

	public AbstractSystem(ISystem parentSystem) {
		this.parentSystem = parentSystem;
		this.subsystems = new ArrayList<ISystem>();
		this.subscriber = new Subscriber();
		this.publisher = new Publisher();
		this.commands = new ArrayList<Command>();
		
		subscriber.start();
		publisher.start();
	}
	
	//ABSTRACT
	public abstract Channel getChannel();

	//PUBLIC METHODS
	public void respond(Command command) {
		System.out.println(getChannel() + " responding to " + command.getCode());
	}
	
	public List<Command> render() {
		List<Command> result = new ArrayList<Command>();
		result.addAll(commands);
		for (ISystem s : subsystems) {
			result.addAll(s.render());
		}
		return result;
	}
	
	//GETTERS
	public List<ISystem> getSubsystems() 	{ return subsystems; 	}
	public ISystem getParentSystem() 		{ return parentSystem; 	}
	
	
	//PUB-SUB THREADS
	protected class Subscriber extends Thread {
		@Override
		public void run() {
			super.run();
			
			Jedis jSubscriber = null;
			try {
				jSubscriber = App.getJedis();
				jSubscriber.subscribe(new JedisPubSub() {
				    @Override
				    public void onMessage(String channel, String message) {
				    	respond(new Command(message));
				    }
				}, getChannel().toString());
			} finally {
				if (jSubscriber != null)
					jSubscriber.close();
			}
			
		}
	}
	
	protected class Publisher extends Thread {
				
		public void publish(String command) {
			Command c = new Command(command);
			Jedis jPublisher = null;
			try {
				jPublisher = App.getJedis();
				jPublisher.publish(c.getChannel().toString(), c.toString());
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