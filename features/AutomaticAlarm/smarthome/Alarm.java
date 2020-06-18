package smarthome;

import business.*;

/**
 * TODO description
 */
public class Alarm extends AbstractSystem {

	public Alarm(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.ALARM;
	}
	
	@Override
	public void respond(Command command) {
		//DO NOTHING
	}
	
}