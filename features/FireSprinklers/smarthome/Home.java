package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedFireSprinklersRules = ruleMap.get(Channel.FIRE_SPRINKLERS);
		if (automatedFireSprinklersRules != null)
			rules.addAll(automatedFireSprinklersRules);
	}

}