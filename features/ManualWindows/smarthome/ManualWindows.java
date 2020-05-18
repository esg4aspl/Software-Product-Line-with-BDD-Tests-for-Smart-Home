package smarthome;

import business.*;
import io.*;

/**
 * TODO description
 */
public class ManualWindows extends AbstractSystem {
	
	public ManualWindows(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public void respond(Command command) {
		System.out.println(getChannel() + " forwarding " + command.getCode() + " to " + Channel.WINDOWS_MANAGEMENT);
		publisher.publish((new Command(Channel.WINDOWS_MANAGEMENT, command.getCode()).toString()));
		
	}
	
	public Channel getChannel() {
		return Channel.MANUAL_WINDOWS;
	}
	
}