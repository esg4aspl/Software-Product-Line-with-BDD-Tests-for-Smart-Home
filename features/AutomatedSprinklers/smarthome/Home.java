package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedSprinklersRules = ruleMap.get(Channel.AUTOMATED_SPRINKLERS);
		if (automatedSprinklersRules != null)
			rules.addAll(automatedSprinklersRules);
	}

}