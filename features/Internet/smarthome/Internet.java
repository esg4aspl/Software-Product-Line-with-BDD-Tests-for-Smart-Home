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
		//System.out.println(getChannel() + " responding to " + command.getCode());
		if (command.getCode().getData().equals("Input")) {
			//Send request here...
		}
	}
	
	
}