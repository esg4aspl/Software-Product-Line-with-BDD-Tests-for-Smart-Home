package smarthome;

import business.*;

/**
 * TODO description
 */
public class BlindsManagement extends AbstractSystem {
	
	public BlindsManagement(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.BLINDS_MANAGEMENT;
	}
	
}