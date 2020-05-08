package smarthome;

import business.Channel;
import business.ISystem;

/**
 * TODO description
 */
public class Alarm {

	public Alarm(ISystem parentSystem) {
		this.subsystems.add(new Siren(this));
	}
	
}