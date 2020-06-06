package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class RemoteVentilationControl extends AbstractSystem {
	
	public RemoteVentilationControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.VENTILATION_CONTROL);
		publisher.publish((new Command(Channel.VENTILATION_CONTROL, command.getCode()).toString()));
	}
	
	public Channel getChannel() {
		return Channel.REMOTE_VENTILATION_CONTROL;
	}
	
}