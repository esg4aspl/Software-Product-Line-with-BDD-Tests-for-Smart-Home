package smarthome;

import business.*;

/**
 * TODO description
 */
public class AirConditioningControl extends AbstractSystem {
	
	public AirConditioningControl(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.AIR_CONDITIONING_CONTROL;
	}
	
}