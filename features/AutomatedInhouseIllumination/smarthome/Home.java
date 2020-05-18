package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedInhouseIlluminationRules = ruleMap.get(Channel.AUTOMATED_INHOUSE_ILLUMINATION);
		if (automatedInhouseIlluminationRules != null)
			rules.addAll(automatedInhouseIlluminationRules);
	}

}