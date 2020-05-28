package smarthome;

import business.*;

/**
 * TODO description
 */
public class FireControl extends AbstractSystem {
	
	public FireControl(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.FIRE_CONTROL;
	}
	
}