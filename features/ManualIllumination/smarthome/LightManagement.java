package smarthome;

import business.*;

/**
 * TODO description
 */
public class LightManagement {
	
	public LightManagement(ISystem parentSystem) {
		this.subsystems.add(new ManualIllumination(this));
	}
	
}