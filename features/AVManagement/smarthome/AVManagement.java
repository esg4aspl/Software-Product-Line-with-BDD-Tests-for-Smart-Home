package smarthome;

import business.*;

/**
 * TODO description
 */
public class AVManagement extends AbstractSystem {
	
	public AVManagement(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.AV_MANAGEMENT;
	}
	
}