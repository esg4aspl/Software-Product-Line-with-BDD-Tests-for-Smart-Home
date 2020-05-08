package smarthome;

import business.*;

/**
 * TODO description
 */
public class GlassbreakSensors extends AbstractSystem {

	public GlassbreakSensors(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.GLASSBREAK_SENSORS;
	}
	
	@Override
	public void respond(Command command) {
		if (!command.getCode().getData().equals("False"))
			publisher.publish(command.getCode().toString() + "@" + Channel.ALARM);
	}
	
}