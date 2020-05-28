package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedMoodsRules = ruleMap.get(Channel.AUTOMATED_MOODS);
		if (automatedMoodsRules != null)
			rules.addAll(automatedMoodsRules);
	}

}