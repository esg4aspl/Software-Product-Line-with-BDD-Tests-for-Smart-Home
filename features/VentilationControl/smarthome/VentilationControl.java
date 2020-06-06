package smarthome;

import business.*;

/**
 * TODO description
 */
public class VentilationControl extends AbstractSystem {
	
	public VentilationControl(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.VENTILATION_CONTROL;
	}
	
}