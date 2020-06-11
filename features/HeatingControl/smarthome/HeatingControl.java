package smarthome;

import business.*;

/**
 * TODO description
 */
public class HeatingControl extends AbstractSystem {
	
	public HeatingControl(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.HEATING_CONTROL;
	}
	
}