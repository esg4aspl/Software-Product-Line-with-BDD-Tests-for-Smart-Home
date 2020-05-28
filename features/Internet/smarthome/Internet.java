package smarthome;

import business.*;

/**
 * TODO description
 */
public class Internet extends AbstractSystem {
	

	public Internet(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.INTERNET;
	}
	
	public void respond(Command command) {
		if (command.getCode().getData().equals("Input")) {
			System.out.println(getChannel() + " creates response.");
			parentSystem.respond(command);
		}
	}
	
	
}