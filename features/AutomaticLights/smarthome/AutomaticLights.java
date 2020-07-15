package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomaticLights extends AbstractSystem {

	public AutomaticLights(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.LIGHTS;
	}
	
	@Override
	public void respond(Command command) {
		output("Lights responding to " + command.getCode());
	}
	
}