package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedBlindsRules = ruleMap.get(Channel.AUTOMATED_BLINDS);
		if (automatedBlindsRules != null)
			rules.addAll(automatedBlindsRules);
	}

}