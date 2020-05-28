package smarthome;

import business.*;

/**
 * TODO description
 */
public class MoodsManagement extends AbstractSystem {
	
	public MoodsManagement(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.MOODS_MANAGEMENT;
	}
	
}