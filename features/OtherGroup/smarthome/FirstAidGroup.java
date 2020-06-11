package smarthome;

import business.*;

/**
 * TODO description
 */
public class FirstAidGroup extends AbstractSystem {

	public void respond(Command command) {
		original(command);
		if (command.getCode().getECode() == ECode.CALL) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				
			}
			output(getChannel() + " calls Other Group.");
		}
	}
	
}