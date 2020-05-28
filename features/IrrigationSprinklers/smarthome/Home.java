package smarthome;

import business.*;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		this.subsystems.add(new IrrigationSprinklers(this));
	}
	
}