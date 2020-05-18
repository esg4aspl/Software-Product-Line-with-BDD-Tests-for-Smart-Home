package smarthome;

import business.Command;

/**
 * TODO description
 */
public class Internet {
	
	public void respond(Command command) {
		if (command.getCode().getData().equals("Input")) {
			//Send encrypted request here...
			System.out.println("Send private input message to internet.");
			System.out.println("Encrypt input with RSA.");
		} else if (command.getCode().getData().equals("Output")) {
			System.out.println("Private output.");
			System.out.println("Decrypt output with RSA.");
		}
	}

}