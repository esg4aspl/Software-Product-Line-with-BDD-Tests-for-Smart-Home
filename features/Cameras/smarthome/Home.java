package smarthome;

import business.*;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		this.subsystems.add(new Cameras(this));
		rules.addAll(ruleMap.get(Channel.CAMERAS));
	}
	
}