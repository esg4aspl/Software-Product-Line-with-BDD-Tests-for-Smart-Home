package smarthome;

import business.*;

/**
 * TODO description
 */
public class VentilationControl {
	
	public VentilationControl(ISystem parentSystem) {
		this.subsystems.add(new ManualVentilationControl(this));
	}
	
}