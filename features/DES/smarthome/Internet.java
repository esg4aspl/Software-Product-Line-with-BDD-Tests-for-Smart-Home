package smarthome;

import business.Command;

/**
 * TODO description
 */
public class Internet {
	
	public void respond(Command command) {
		original(command);
		if (command.getCode().getData().equals("Input")) {
			System.out.println(getChannel() + " creates DES encrypted response.");
		}
	}

}