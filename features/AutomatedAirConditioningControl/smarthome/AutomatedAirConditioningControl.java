package smarthome;
import java.util.List;

import business.*;

/**
 * TODO description
 */
public class AutomatedAirConditioningControl extends AbstractSystem {
	
	public AutomatedAirConditioningControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.AUTOMATED_AIR_CONDITIONING_CONTROL;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}


}