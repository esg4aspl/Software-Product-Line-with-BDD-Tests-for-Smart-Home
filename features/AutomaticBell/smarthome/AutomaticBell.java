package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomaticBell extends AbstractSystem {

	public AutomaticBell(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.BELL;
	}
	
	@Override
	public void respond(Command command) {
		output("Bell responding to " + command.getCode());
	}
	
}