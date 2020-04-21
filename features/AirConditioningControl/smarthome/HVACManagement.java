package smarthome;

/**
 * TODO description
 */
public class HVACManagement {
	
	public HVACManagement(ISystem parentSystem) {
		this.subsystems.add(new AirConditioningControl(this));
	}

}