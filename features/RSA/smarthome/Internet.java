package smarthome;

import business.Command;
import business.InternetBag;

/**
 * TODO description
 */
public class Internet {
	
	private boolean acceptInput = false;
	
	public void respond(Command command) throws Exception {
		if (command.getCode().getData().equals("Input")) {
			if (!acceptInput)
				acceptInput = true;
			else
				acceptInput = false;
			output(getChannel() + " activates EcryptedInput accept mode.");
			InternetBag.getInstance().addMessage(getChannel() + " creates response.");
		} else if (command.getCode().getData().equals("EncryptedInput")) {
			if (acceptInput) {
				InternetBag.getInstance().addMessage(getChannel() + " creates RSA encrypted response.");
				output(getChannel() + " responding to input.");
				acceptInput = false;
			} else {
				InternetBag.getInstance().addMessage(getChannel() + " creates error message.");
				output("EncryptedInput error!");
			}
		}
	}

}