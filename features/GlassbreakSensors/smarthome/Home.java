package smarthome;

import business.*;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		this.subsystems.add(new GlassbreakSensors(this));
		rules.addAll(ruleMap.get(Channel.GLASSBREAK_SENSORS));
	}
	
}