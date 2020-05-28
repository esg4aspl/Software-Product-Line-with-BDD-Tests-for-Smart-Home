package smarthome;

import business.*;

/**
 * TODO description
 */
public class IrrigationSprinklers {
	
	public IrrigationSprinklers(ISystem parentSystem) {
		this.subsystems.add(new AutomatedSprinklers(this));
	}
	
}