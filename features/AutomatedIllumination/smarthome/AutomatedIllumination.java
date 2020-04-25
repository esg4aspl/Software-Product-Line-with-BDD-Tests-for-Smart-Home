package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomatedIllumination extends AbstractSystem {
	
	public AutomatedIllumination(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.AUTOMATED_ILLUMINATION;
	}
	
}