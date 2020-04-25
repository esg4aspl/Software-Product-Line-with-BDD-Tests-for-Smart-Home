package smarthome;

import business.*;

/**
 * TODO description
 */
public class LightManagement extends AbstractSystem {
	
	public LightManagement(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.LIGHT_MANAGEMENT;
	}
	
}