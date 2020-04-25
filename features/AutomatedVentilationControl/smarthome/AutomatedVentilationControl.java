package smarthome;

import java.util.List;
import business.*;

/**
 * TODO description
 */
public class AutomatedVentilationControl extends AbstractSystem {
	
	public AutomatedVentilationControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.AUTOMATED_VENTILATION_CONTROL;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}

}