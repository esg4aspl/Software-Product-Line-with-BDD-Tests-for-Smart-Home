package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedAVRules = ruleMap.get(Channel.AUTOMATED_AV);
		if (automatedAVRules != null)
			rules.addAll(automatedAVRules);
	}

}