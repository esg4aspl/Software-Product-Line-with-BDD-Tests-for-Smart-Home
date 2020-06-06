package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedVentilationRules = ruleMap.get(Channel.AUTOMATED_VENTILATION_CONTROL);
		if (automatedVentilationRules != null)
			rules.addAll(automatedVentilationRules);
	}

}