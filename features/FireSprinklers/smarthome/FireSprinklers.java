package smarthome;

import business.*;

/**
 * TODO description
 */
public class FireSprinklers extends AbstractSystem {

	public FireSprinklers(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.FIRE_SPRINKLERS;
	}
	
	@Override
	public void respond(Command command) throws Exception {
		if (parentSystem instanceof Home) {
			if (!((Home) parentSystem).isStarted())
				return;
		}
		if (command.getCode().getECode() == ECode.TURN_ON) {	
			try {
				Thread.sleep(75);
			} catch (Exception e) {
				
			}
		}
		output(getChannel() + " responding to " + command.getCode());
		//System.out.println(getChannel() + " responding to " + command.getCode());
	}
	
}