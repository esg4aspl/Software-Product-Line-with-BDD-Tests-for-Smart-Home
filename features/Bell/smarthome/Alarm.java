package smarthome;

/**
 * TODO description
 */
public class Alarm {

	public Alarm(ISystem parentSystem) {
		this.subsystems.add(new Bell(this));
	}
	
}