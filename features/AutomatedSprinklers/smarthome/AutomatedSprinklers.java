package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomatedSprinklers extends AbstractSystem {
	
	private boolean automation;
	
	public AutomatedSprinklers(ISystem parentSystem) {
		super(parentSystem);
		automation = true;
	}
	
	public Channel getChannel() {
		return Channel.AUTOMATED_SPRINKLERS;
	}
	
	@Override
	public void respond(Command command) throws Exception {
		if (command.getCode().getECode() != ECode.AUTOMATE) {
			if (!automation)
				return;
			output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.IRRIGATION_SPRINKLERS);
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