package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class ManualVentilationControl extends AbstractSystem {
	
	public ManualVentilationControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.VENTILATION_CONTROL);
		publisher.publish((new Command(Channel.VENTILATION_CONTROL, command.getCode()).toString()));
	}
	
	public Channel getChannel() {
		return Channel.MANUAL_VENTILATION_CONTROL;
	}
	
}