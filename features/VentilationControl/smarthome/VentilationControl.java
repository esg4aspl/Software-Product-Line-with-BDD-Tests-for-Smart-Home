package smarthome;

import java.util.List;
import business.*;

/**
 * TODO description
 */
public class VentilationControl extends AbstractSystem {
	
	public VentilationControl(ISystem parentSystem) {
		super(parentSystem);
	}
	
	@Override
	public Channel getChannel() {
		return Channel.VENTILATION_CONTROL;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}
	

}