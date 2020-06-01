package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomatedBlinds extends AbstractSystem {
	
	private boolean automation;
	
	public AutomatedBlinds(ISystem parentSystem) {
		super(parentSystem);
		automation = true;
	}
	
	public Channel getChannel() {
		return Channel.AUTOMATED_BLINDS;
	}
	
	@Override
	public void respond(Command command) {
		if (command.getCode().getECode() != ECode.AUTOMATE) {
			if (!automation)
				return;
			output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.BLINDS_MANAGEMENT);
			parentSystem.respond(command);
		} else {
			if (command.getCode().getData().equals("On")) {
				automation = true;
				output(getChannel() + " has turned on automation.");
			} else if (command.getCode().getData().equals("Off")) {
				automation = false;
				output(getChannel() + " has turned off automation.");
			}
		}
	}
	
}