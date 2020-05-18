package smarthome;

import business.Channel;
import business.Command;
import business.ISystem;

/**
 * TODO description
 */
public class Internet {

	public Internet(ISystem parentSystem) {
		this.subsystems.add(new Privacy(this));
	}
	
	public void respond(Command command) {
		if (command.getCode().getData().equals("Input")) {
			//Send private request here...
			System.out.println("Send private input message to internet.");
		} else if (command.getCode().getData().equals("Output")) {
			System.out.println("Private output.");
		}
	}
	
}