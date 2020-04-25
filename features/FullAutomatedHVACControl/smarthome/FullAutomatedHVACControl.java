package smarthome;

import java.util.List;
import business.*;

/**
 * TODO description
 */
public class FullAutomatedHVACControl extends AbstractSystem {
	
	public FullAutomatedHVACControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public Channel getChannel() {
		return Channel.FULL_AUTOMATED_HVAC_CONTROL;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}
	

}