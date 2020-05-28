package smarthome;

import business.*;

/**
 * TODO description
 */
public class FireSprinklers extends AbstractSystem {

	public FireSprinklers(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.FIRE_SPRINKLERS;
	}
	
}