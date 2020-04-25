package smarthome;

import business.ISystem;

/**
 * TODO description
 */
public class AutomatedAirConditioningControl {
	
	public AutomatedAirConditioningControl(ISystem parentSystem) {
		this.subsystems.add(new RemoteAirConditioningControl(this));
	}

}