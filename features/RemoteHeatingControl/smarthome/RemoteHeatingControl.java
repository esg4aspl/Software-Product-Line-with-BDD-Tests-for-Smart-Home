package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class RemoteHeatingControl extends AbstractSystem {
	
	public RemoteHeatingControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.HEATING_CONTROL);
		publisher.publish((new Command(Channel.HEATING_CONTROL, command.getCode()).toString()));
	}
	
	public Channel getChannel() {
		return Channel.REMOTE_HEATING_CONTROL;
	}
	
}