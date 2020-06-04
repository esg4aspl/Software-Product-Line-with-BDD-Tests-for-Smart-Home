package smarthome;

import business.AbstractSystem;
import business.Channel;
import business.Command;
import business.ISystem;

/**
 * TODO description
 */
public class Siren extends AbstractSystem {

	public Siren(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.SIREN;
	}
	
	@Override
	public void respond(Command command) {
		output("Siren responding to " + command.getCode());
	}
	
}