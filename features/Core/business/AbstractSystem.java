package business;

import java.util.List;

import application.App;
import io.CommandReader;

import java.util.ArrayList;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * TODO description
 */
public abstract class AbstractSystem implements ISystem {
	
	protected List<ISystem> subsystems;
	protected ISystem parentSystem;
	protected ISubscriber subscriber;
	protected IPublisher publisher;
	protected List<Command> commands;
	
	//CONSTRUCTORS
	public AbstractSystem() {
		this(null);
	}
	
	public void kill() { 
		publisher.kill();
		subscriber.kill();
		for (ISystem s : subsystems) {
			s.kill();
		}
	}

	public AbstractSystem(ISystem parentSystem) {
		this.parentSystem = parentSystem;
		this.subsystems = new ArrayList<ISystem>();
		this.subscriber = createSubscriber();
		this.publisher = createPublisher();
		this.commands = new ArrayList<Command>();
		List<Command> commandsToAdd = CommandReader.getInstance().getMap().get(getChannel());
		if (commandsToAdd != null)
			this.commands.addAll(commandsToAdd);
		
		subscriber.start();
		publisher.start();
	}
	
	//ABSTRACT
	public abstract Channel getChannel();
	
	public void addSubsystem(ISystem subsystem) {
		this.subsystems.add(subsystem);
	}

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
	protected ISubscriber createSubscriber() {
		return new Subscriber();
	}
	
	protected IPublisher createPublisher() {
		return new Publisher();
	}
	
	protected class Subscriber extends Thread implements ISubscriber {
		private boolean alive = true;
		
		@Override
		public void run() {
			super.run();
			
			Jedis jSubscriber = null;
			try {
				jSubscriber = App.getJedis();
				jSubscriber.subscribe(new JedisPubSub() {
				    @Override
				    public void onMessage(String channel, String message) {
				    	if (alive)
				    		respond(new Command(message));
				    }
				}, getChannel().toString());
			} finally {
				if (jSubscriber != null)
					jSubscriber.close();
			}
			
		}
		
		public void kill() {
			alive = false;
		}
	}
	
	protected class Publisher extends Thread implements IPublisher {
		private boolean alive = true;
		
		public void publish(String command) {
			if (!alive)
				return;
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
		
		public void kill() {
			alive = false;
		}
	}

}