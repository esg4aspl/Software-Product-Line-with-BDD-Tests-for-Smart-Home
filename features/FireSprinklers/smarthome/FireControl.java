package smarthome;

import business.*;

/**
 * TODO description
 */
public class FireControl {
	
	public FireControl(ISystem parentSystem) {
		this.subsystems.add(new FireSprinklers(this));
	}
	
}