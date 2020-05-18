package smarthome;

import business.*;

/**
 * TODO description
 */
public class WindowsManagement extends AbstractSystem {
	
	public WindowsManagement(ISystem parentSystem) {
		super(parentSystem);
	}

	public Channel getChannel() {
		return Channel.WINDOWS_MANAGEMENT;
	}
	
}