package smarthome;

import java.util.List;
import business.*;

/**
 * TODO description
 */
public class FullManualHVACControl extends AbstractSystem {
	
	public FullManualHVACControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public Channel getChannel() {
		return Channel.FULL_MANUAL_HVAC_CONTROL;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}
	

}