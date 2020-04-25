package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomatedIllumination {
	
	public AutomatedIllumination(ISystem parentSystem) {
		this.subsystems.add(new AutomatedInhouseIllumination(this));
	}
	
}