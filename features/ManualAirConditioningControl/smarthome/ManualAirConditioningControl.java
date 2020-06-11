package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class ManualAirConditioningControl extends AbstractSystem {
	
	public ManualAirConditioningControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.AIR_CONDITIONING_CONTROL);
		publisher.publish((new Command(Channel.AIR_CONDITIONING_CONTROL, command.getCode()).toString()));
	}
	
	public Channel getChannel() {
		return Channel.MANUAL_AIR_CONDITIONING_CONTROL;
	}
	
}