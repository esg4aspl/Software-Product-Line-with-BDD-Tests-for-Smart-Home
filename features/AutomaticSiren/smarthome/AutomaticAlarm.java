package smarthome;

import business.Channel;
import business.ISystem;

/**
 * TODO description
 */
public class AutomaticAlarm {

	public AutomaticAlarm(ISystem parentSystem) {
		this.subsystems.add(new AutomaticSiren(this));
	}
	
}