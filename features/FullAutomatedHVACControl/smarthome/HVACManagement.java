package smarthome;

import business.*;

/**
 * TODO description
 */
public class HVACManagement {
	
	public HVACManagement(ISystem parentSystem) {
		this.subsystems.add(new FullAutomatedHVACControl(this));
	}

}