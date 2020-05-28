package smarthome;

import business.*;

/**
 * TODO description
 */
public class BlindsManagement {
	
	public BlindsManagement(ISystem parentSystem) {
		this.subsystems.add(new AutomatedBlinds(this));
	}
	
}