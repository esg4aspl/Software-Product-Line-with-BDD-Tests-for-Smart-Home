package smarthome;

import business.*;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		this.subsystems.add(new AutomaticAlarm(this));
		
		List<Rule> automatedAlarmRules = ruleMap.get(Channel.ALARM);
		if (automatedAlarmRules != null)
			rules.addAll(automatedAlarmRules);
	}
	
}