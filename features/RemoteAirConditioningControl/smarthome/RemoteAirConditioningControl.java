package smarthome;

import java.util.List;

import business.*;

/**
 * TODO description
 */
public class RemoteAirConditioningControl extends AbstractSystem {
	
	public RemoteAirConditioningControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.REMOTE_AIR_CONDITIONING_CONTROL;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}
}