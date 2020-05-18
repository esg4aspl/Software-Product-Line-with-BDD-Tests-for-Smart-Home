package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedWindowsRules = ruleMap.get(Channel.AUTOMATED_WINDOWS);
		if (automatedWindowsRules != null)
			rules.addAll(automatedWindowsRules);
	}

}