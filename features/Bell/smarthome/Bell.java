package smarthome;

import business.*;

/**
 * TODO description
 */
public class Bell extends AbstractSystem {

	public Bell(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.ALARM;
	}
	
	@Override
	public void respond(Command command) {
		output("Bell responding to " + command.getCode());
	}
	
}