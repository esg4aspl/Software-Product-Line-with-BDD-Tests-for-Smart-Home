package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedHeatingRules = ruleMap.get(Channel.AUTOMATED_HEATING);
		if (automatedHeatingRules != null)
			rules.addAll(automatedHeatingRules);
	}

}