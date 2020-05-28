package smarthome;

import business.*;

/**
 * TODO description
 */
public class IrrigationSprinklers extends AbstractSystem {
	
	public IrrigationSprinklers(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.IRRIGATION_SPRINKLERS;
	}
	
}