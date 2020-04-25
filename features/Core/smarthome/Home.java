package smarthome;

import business.*;

/**
 * TODO description
 */
public class Home extends AbstractSystem {
	
	public Home() {
		super();
	}
	
	public Channel getChannel() {
		return Channel.HOME;
	}
	
	public void init() {
		for (ISystem s : subsystems) {
			if (s instanceof UI) {
				((UI) s).init();
			}
		}
	}
	
}