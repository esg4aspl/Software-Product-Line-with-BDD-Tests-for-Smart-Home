package smarthome;

import business.Code;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		this.subsystems.add(new Alarm(this));
	}

}