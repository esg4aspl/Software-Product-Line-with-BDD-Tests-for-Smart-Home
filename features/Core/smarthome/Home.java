package smarthome;

import business.*;

/**
 * TODO description
 */
public class Home extends AbstractSystem {
	
	public Home() {
		super();
		commands.add(new Command("TURN_ON=All Locks@HOME"));
		commands.add(new Command("TURN_OFF=All Locks@HOME"));
	}
	
	public Channel getChannel() {
		return Channel.HOME;
	}
	
}