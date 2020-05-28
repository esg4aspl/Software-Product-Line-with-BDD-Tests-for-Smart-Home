package smarthome;

import business.*;

/**
 * TODO description
 */
public class AVManagement {
	
	public AVManagement(ISystem parentSystem) {
		this.subsystems.add(new AutomatedAV(this));
	}
	
}