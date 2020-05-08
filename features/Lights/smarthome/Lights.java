package smarthome;

import business.*;

/**
 * TODO description
 */
public class Lights extends AbstractSystem {

	public Lights(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.ALARM;
	}
	
	@Override
	public void respond(Command command) {
		System.out.println("Lights responding to " + command.getCode());
	}
	
}