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
			System.out.println(getChannel() + " forwarding " + command.getCode() + " to " + Channel.MOODS_MANAGEMENT);
			parentSystem.respond(command);
			//publisher.publish((new Command(Channel.WINDOWS_MANAGEMENT, command.getCode()).toString()));
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