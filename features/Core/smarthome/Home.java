package smarthome;

import java.util.ArrayList;
import java.util.List;
import business.*;

/**
 * TODO description
 */
public class Home extends AbstractSystem {
	
	public Home() {
		super();
	}
	
	public Channel getChannel() {
		return Channel.HOME;
	}
	
	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}
	
}