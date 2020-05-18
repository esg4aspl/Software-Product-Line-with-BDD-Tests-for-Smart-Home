package smarthome;

import business.*;

/**
 * TODO description
 */
public class DES extends AbstractSystem {
	

	public DES(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.DES;
	}
	
	
}