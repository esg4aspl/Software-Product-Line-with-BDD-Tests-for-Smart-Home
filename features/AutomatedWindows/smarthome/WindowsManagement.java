package smarthome;

import business.*;

/**
 * TODO description
 */
public class WindowsManagement {
	
	public WindowsManagement(ISystem parentSystem) {
		this.subsystems.add(new AutomatedWindows(this));
	}
	
}