package smarthome;

import java.util.List;

import business.*;

/**
 * TODO description
 */
public class Security extends AbstractSystem {
	
	public Security(ISystem parentSystem) {
		super(parentSystem);
	}
	
	public Channel getChannel() {
		return Channel.SECURITY;
	}
	
	@Override
	public List<Code> render() {
		// TODO Auto-generated method stub
		return null;
	}

}