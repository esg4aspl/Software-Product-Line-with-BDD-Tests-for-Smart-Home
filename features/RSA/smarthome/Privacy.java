package smarthome;

import business.Channel;
import business.Command;
import business.ISystem;

/**
 * TODO description
 */
public class Privacy {

	public Privacy(ISystem parentSystem) {
		this.subsystems.add(new RSA(this));
	}
	
}