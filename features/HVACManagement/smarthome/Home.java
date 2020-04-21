package smarthome;

import java.util.ArrayList;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		this.subsystems.add(new HVACManagement(this));
	}

}