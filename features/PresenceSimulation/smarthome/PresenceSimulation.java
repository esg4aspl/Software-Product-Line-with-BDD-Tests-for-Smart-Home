package smarthome;

import business.*;

/**
 * TODO description
 */
public class PresenceSimulation extends AbstractSystem {
	
	public PresenceSimulation(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.PRESENCE_SIMULATION;
	}
	
}