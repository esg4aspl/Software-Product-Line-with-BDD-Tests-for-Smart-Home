package smarthome;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		this.subsystems.add(new Alarm(this));
	}

}