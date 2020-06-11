package business;



import static org.junit.Assert.assertTrue;
import java.util.List;

import application.App;
import io.CommandReader;
import smarthome.Home;

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
		subscriber.kill();
		publisher.kill();
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
//		publisher.start();
	}
	
	protected void output(String str) {
		OutputBag.getInstance().addOutput(str);
	}
	
	//ABSTRACT
	public abstract Channel getChannel();
	
	public void addSubsystem(ISystem subsystem) {
		this.subsystems.add(subsystem);
	}

	//PUBLIC METHODS
	public void respond(Command command) throws Exception {
		if (parentSystem instanceof Home) {
			if (!((Home) parentSystem).isStarted())
				return;
		}
		output(getChannel() + " responding to " + command.getCode());
		//System.out.println(getChannel() + " responding to " + command.getCode());
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
		Jedis jSubscriber = null;
		String cn = "";
		
		public Subscriber() {
			super();
			jSubscriber = App.getJedis();
		}
		
		@Override
		public void run() {
			cn = getChannel().toString();
			
			try {
				jSubscriber.subscribe(new JedisPubSub() {
				    @Override
				    public void onMessage(String channel, String message) {
				    	if (!alive) {
				    		unsubscribe();
				    		return;
				    	}
				    	
				    	try {
				    		if (alive)
				    			respond(new Command(message));
				    	} catch (Exception e) {
				    		
				    	}
				    }
				}, cn);
			} finally {
				if (jSubscriber != null)
					jSubscriber.close();
			}
		}
		
		public void kill() {
			alive = false;
			publisher.publish("ANYTHING=ANYTHING@" + cn);
		}
		
	}
	
//	protected class Publisher implements IPublisher {
//		private boolean alive = true;
//		
//		public void publish(String command) {
//			if (!alive)
//				return;
//			Command c = new Command(command);
//			Jedis jPublisher = App.getPublisher();
//			jPublisher.publish(c.getChannel().toString(), c.toString());
//		}
//		
//		public void start() {
//			
//		}
//		
//		public void kill() {
//			alive = false;
//		}
//		
//		
//	}
	
	
	protected class Publisher implements IPublisher {
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
		
		public void start() {
			//super.start();
		}
		
		public void run() {
//			super.run();
		}
		
		public void kill() {
			alive = false;
		}
	}

}