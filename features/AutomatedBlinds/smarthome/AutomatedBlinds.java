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
			System.out.println(getChannel() + " forwarding " + command.getCode() + " to " + Channel.BLINDS_MANAGEMENT);
			parentSystem.respond(command);
		} else {
			if (command.getCode().getData().equals("On")) {
				automation = true;
				System.out.println(getChannel() + " has turned on automation.");
			} else if (command.getCode().getData().equals("Off")) {
				automation = false;
				System.out.println(getChannel() + " has turned off automation.");
			}
		}
	}
	
}