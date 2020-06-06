package smarthome;

import business.*;

/**
 * TODO description
 */
public class AutomatedVentilationControl extends AbstractSystem {
	
	private boolean automation;
	
	public AutomatedVentilationControl(ISystem parentSystem) {
		super(parentSystem);
		automation = true;
	}
	
	public Channel getChannel() {
		return Channel.AUTOMATED_VENTILATION_CONTROL;
	}
	
	@Override
	public void respond(Command command) throws Exception {
		if (command.getCode().getECode() != ECode.AUTOMATE) {
			if (!automation)
				return;
			output(getChannel() + " forwarding " + command.getCode() + " to " + Channel.VENTILATION_CONTROL);
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