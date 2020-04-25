package smarthome;

import java.util.List;

import business.*;

/**
 * TODO description
 */
public class Bell extends AbstractSystem {

	public Bell(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		//Bell is a type of Alarm
		return Channel.ALARM;
	}

	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}

}