package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomatedMoods extends AbstractSystem {
	
	private boolean automation;
	
	public AutomatedMoods(ISystem parentSystem) {
		super(parentSystem);
		automation = true;
	}
	
	public Channel getChannel() {
		return Channel.AUTOMATED_MOODS;
	}
	
	@Override
	public void respond(Command command) {
		if (command.getCode().getECode() != ECode.AUTOMATE) {
			if (!automation)
				return;
			output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.MOODS_MANAGEMENT);
			parentSystem.respond(command);
			//publisher.publish((new Command(Channel.WINDOWS_MANAGEMENT, command.getCode()).toString()));
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