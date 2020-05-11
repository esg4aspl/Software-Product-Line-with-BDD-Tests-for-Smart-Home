package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomatedPerimeterIllumination extends AbstractSystem {
	
	private boolean automation;
	
	public AutomatedPerimeterIllumination(ISystem parentSystem) {
		super(parentSystem);
		automation = true;
	}
	
	public Channel getChannel() {
		return Channel.AUTOMATED_PERIMETER_ILLUMINATION;
	}
	
	@Override
	public void respond(Command command) {
		if (command.getCode().getECode() != ECode.AUTOMATE) {
			if (!automation)
				return;
			System.out.println(getChannel() + " forwarding " + command.getCode() + " to " + Channel.LIGHT_MANAGEMENT);
			publisher.publish((new Command(Channel.LIGHT_MANAGEMENT, command.getCode()).toString()));
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