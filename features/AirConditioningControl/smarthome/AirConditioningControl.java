package smarthome;

import java.util.List;
import business.*;

/**
 * TODO description
 */
public class AirConditioningControl extends AbstractSystem {
	
	public AirConditioningControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public Channel getChannel() {
		return Channel.AIR_CONDITIONING_CONTROL;
	}

	@Override
	public List<String> render() {
		// TODO Auto-generated method stub
		return null;
	}
	

}