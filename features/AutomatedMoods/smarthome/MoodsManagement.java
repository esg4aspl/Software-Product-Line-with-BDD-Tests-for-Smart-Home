package smarthome;

import business.*;

/**
 * TODO description
 */
public class MoodsManagement {
	
	public MoodsManagement(ISystem parentSystem) {
		this.subsystems.add(new AutomatedMoods(this));
	}
	
}