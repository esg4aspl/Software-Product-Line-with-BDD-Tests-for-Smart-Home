package smarthome;

import business.*;

/**
 * TODO description
 */
public class Cameras extends AbstractSystem {

	public Cameras(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.CAMERAS;
	}
	
	@Override
	public void respond(Command command) {
		if (!command.getCode().getData().equals("False"))
			publisher.publish(command.getCode().toString() + "@" + Channel.ALARM);
	}
	
}