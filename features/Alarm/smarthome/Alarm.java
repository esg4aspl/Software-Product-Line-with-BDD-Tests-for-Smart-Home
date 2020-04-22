package smarthome;

import java.util.List;
import business.*;

/**
 * TODO description
 */
public class Alarm extends AbstractSystem {
	
	public Alarm(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.ALARM;
	}

	@Override
	public List<String> render() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}