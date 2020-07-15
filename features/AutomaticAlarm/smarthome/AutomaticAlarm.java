package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomaticAlarm extends AbstractSystem {

	public AutomaticAlarm(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.ALARM;
	}
	
	@Override
	public void respond(Command command) {
		//DO NOTHING
	}
	
}