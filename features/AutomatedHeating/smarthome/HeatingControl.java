package smarthome;

import business.*;

/**
 * TODO description
 */
public class HeatingControl {
	
	public HeatingControl(ISystem parentSystem) {
		this.subsystems.add(new AutomatedHeating(this));
	}
	
}