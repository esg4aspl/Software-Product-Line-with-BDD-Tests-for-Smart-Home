package smarthome;

import business.Command;
import business.EncryptedInternetBag;
import business.InternetBag;

/**
 * TODO description
 */
public class Internet {
	
	private boolean acceptInput = false;
	
	public void respond(Command command) throws Exception {
		original(command);
		if (command.getCode().getData().equals("Input")) {
			if (!acceptInput)
				acceptInput = true;
			else
				acceptInput = false;
			transferCompleted = false;
			InternetBag.getInstance().addMessage(getChannel() + " has not completed transfer.");
		} else if (command.getCode().getData().equals("EncryptedInput")) {
			if (acceptInput) {
				EncryptedInternetBag.getInstance().addMessage(getChannel() + " creates DES encrypted response.");
				output(getChannel() + " responding to input.");
				acceptInput = false;
				transferCompleted = true;
				InternetBag.getInstance().addMessage(getChannel() + " completed transfer.");
			} else {
				InternetBag.getInstance().addMessage(getChannel() + " creates error message.");
				output("EncryptedInput error!");
			}
		}
	}

}