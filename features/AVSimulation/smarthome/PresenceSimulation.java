package smarthome;

import business.*;

/**
 * TODO description
 */
public class PresenceSimulation {
	
	public PresenceSimulation(ISystem parentSystem) {
		this.subsystems.add(new AVSimulation(this));
	}
	
}