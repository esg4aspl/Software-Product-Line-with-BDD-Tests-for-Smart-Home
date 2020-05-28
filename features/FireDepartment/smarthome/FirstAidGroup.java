package smarthome;

import business.*;

/**
 * TODO description
 */
public class FirstAidGroup extends AbstractSystem {

	public FirstAidGroup(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.FIRST_AID_GROUP;
	}
	
	@Override
	public void respond(Command command) {
		if (command.getCode().getECode() == ECode.CALL)
			System.out.println(getChannel() + " calls Fire Department.");
	}
}