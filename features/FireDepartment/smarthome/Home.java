package smarthome;

import java.util.ArrayList;
import java.util.List;

import business.Channel;
import business.Code;
import business.Command;
import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		List<Rule> automatedFireRules = ruleMap.get(Channel.FIRST_AID_GROUP);
		if (automatedFireRules != null)
			rules.addAll(automatedFireRules);
	}

}