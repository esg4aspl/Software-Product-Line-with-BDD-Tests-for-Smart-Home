package smarthome;

import business.Channel;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedPerimeterIlluminationRules = ruleMap.get(Channel.AUTOMATED_PERIMETER_ILLUMINATION);
		if (automatedPerimeterIlluminationRules != null)
			rules.addAll(automatedPerimeterIlluminationRules);
	}

}