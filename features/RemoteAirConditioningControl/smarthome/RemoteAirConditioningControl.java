package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class RemoteAirConditioningControl extends AbstractSystem {
	
	public RemoteAirConditioningControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.AIR_CONDITIONING_CONTROL);
		publisher.publish((new Command(Channel.AIR_CONDITIONING_CONTROL, command.getCode()).toString()));
	}
	
	public Channel getChannel() {
		return Channel.REMOTE_AIR_CONDITIONING_CONTROL;
	}
	
}