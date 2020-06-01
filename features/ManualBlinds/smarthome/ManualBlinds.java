package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class ManualBlinds extends AbstractSystem {
	
	public ManualBlinds(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.BLINDS_MANAGEMENT);
		publisher.publish((new Command(Channel.BLINDS_MANAGEMENT, command.getCode()).toString()));
		
	}
	
	public Channel getChannel() {
		return Channel.MANUAL_BLINDS;
	}
	
}