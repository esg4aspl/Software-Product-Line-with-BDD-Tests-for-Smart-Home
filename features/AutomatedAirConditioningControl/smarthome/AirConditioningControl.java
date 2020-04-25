package smarthome;

import business.ISystem;

/**
 * TODO description
 */
public class AirConditioningControl {

	public AirConditioningControl(ISystem parentSystem) {
		this.subsystems.add(new AutomatedAirConditioningControl(this));
	}
	
}