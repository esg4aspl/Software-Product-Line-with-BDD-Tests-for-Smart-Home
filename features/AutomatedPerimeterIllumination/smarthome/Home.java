package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		rules.addAll(ruleMap.get(Channel.AUTOMATED_PERIMETER_ILLUMINATION));
	}

}