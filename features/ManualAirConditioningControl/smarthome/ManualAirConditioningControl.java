package smarthome;

import java.util.List;
import business.*;

/**
 * TODO description
 */
public class ManualAirConditioningControl extends AbstractSystem {
	
	public ManualAirConditioningControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.MANUAL_AIR_CONDITIONING_CONTROL;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}

}