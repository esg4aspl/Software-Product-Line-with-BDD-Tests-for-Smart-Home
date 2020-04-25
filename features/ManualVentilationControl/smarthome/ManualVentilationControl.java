package smarthome;

import java.util.List;
import business.*;

/**
 * TODO description
 */
public class ManualVentilationControl extends AbstractSystem {
	
	public ManualVentilationControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.MANUAL_VENTILATION_CONTROL;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}

}