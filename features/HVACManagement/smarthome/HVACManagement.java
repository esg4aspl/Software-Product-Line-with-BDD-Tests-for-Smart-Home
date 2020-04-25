package smarthome;

import java.util.List;

/**
 * TODO description
 */
public class HVACManagement extends AbstractSystem {

	public HVACManagement(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.HVAC_MANAGEMENT;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}
	

}