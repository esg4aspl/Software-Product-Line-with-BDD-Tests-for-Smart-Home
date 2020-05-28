package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class ManualSprinklers extends AbstractSystem {
	
	public ManualSprinklers(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		System.out.println(getChannel() + " forwarding " + command.getCode() + " to " + Channel.IRRIGATION_SPRINKLERS);
		publisher.publish((new Command(Channel.IRRIGATION_SPRINKLERS, command.getCode()).toString()));
	}
	
	public Channel getChannel() {
		return Channel.MANUAL_SPRINKLERS;
	}
	
}