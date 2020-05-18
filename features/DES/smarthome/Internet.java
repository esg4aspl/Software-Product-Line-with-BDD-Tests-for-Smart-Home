package smarthome;

import business.Command;

/**
 * TODO description
 */
public class Internet {
	
	public void respond(Command command) {
		if (command.getCode().getData().equals("Input")) {
			//Send encrypted request here...
			System.out.println("Encrypt input message with DES.");
		} else if (command.getCode().getData().equals("Output")) {
			System.out.println("Decrypt output message with DES.");
		}
	}

}