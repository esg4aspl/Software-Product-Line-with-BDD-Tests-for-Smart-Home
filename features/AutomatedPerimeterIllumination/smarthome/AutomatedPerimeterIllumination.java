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
	public void respond(Command command) throws Exception {
		if (command.getCode().getECode() != ECode.AUTOMATE) {
			if (!automation)
				return;
			output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.LIGHT_MANAGEMENT);
			parentSystem.getParentSystem().respond(command);
			//publisher.publish((new Command(Channel.LIGHT_MANAGEMENT, command.getCode()).toString()));
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