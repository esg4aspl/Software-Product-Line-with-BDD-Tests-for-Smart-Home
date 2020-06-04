package smarthome;

import business.*;

/**
 * TODO description
 */
public class Internet extends AbstractSystem {
	
	private boolean transferCompleted;
	
	public Internet(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.INTERNET;
	}
	
	public void respond(Command command) {
		if (command.getCode().getData().equals("Input")) {
			InternetBag.getInstance().addMessage(getChannel() + " creates response.");
			InternetBag.getInstance().addMessage(getChannel() + " completed transfer.");
			output(getChannel() + " responding to input.");
			transferCompleted = true;
		}
	}
	
}