package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedAirConditioningRules = ruleMap.get(Channel.AUTOMATED_AIR_CONDITIONING_CONTROL);
		if (automatedAirConditioningRules != null)
			rules.addAll(automatedAirConditioningRules);
	}

}