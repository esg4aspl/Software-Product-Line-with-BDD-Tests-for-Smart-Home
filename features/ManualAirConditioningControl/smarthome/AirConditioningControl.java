package smarthome;

import business.*;

/**
 * TODO description
 */
public class AirConditioningControl {
	
	public AirConditioningControl(ISystem parentSystem) {
		this.subsystems.add(new ManualAirConditioningControl(this));
	}
	
}