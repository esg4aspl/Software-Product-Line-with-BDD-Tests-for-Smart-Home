package smarthome;

import business.*;

/**
 * TODO description
 */
public class FirstAidGroup extends AbstractSystem {

	public void respond(Command command) {
		original(command);
		if (command.getCode().getECode() == ECode.CALL)
			output(getChannel() + " calls Other Group.");
	}
	
}