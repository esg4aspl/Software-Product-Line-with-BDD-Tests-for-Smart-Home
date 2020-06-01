package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class ManualIllumination extends AbstractSystem {
	
	public ManualIllumination(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.LIGHT_MANAGEMENT);
		publisher.publish((new Command(Channel.LIGHT_MANAGEMENT, command.getCode()).toString()));
		
	}
	
	public Channel getChannel() {
		return Channel.MANUAL_ILLUMINATION;
	}
	
}