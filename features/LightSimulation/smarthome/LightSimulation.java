package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class LightSimulation extends AbstractSystem {
	
	public LightSimulation(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.PRESENCE_SIMULATION);
		publisher.publish((new Command(Channel.PRESENCE_SIMULATION, command.getCode()).toString()));
		
	}
	
	public Channel getChannel() {
		return Channel.LIGHT_SIMULATION;
	}
	
}