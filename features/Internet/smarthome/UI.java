package smarthome;

import business.Channel;
import business.ISystem;

/**
 * TODO description
 */
public class UI {

	public UI(ISystem parentSystem) {
		this.subsystems.add(new Internet(this));
	}
	
}