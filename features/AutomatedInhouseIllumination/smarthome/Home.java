package smarthome;

import business.Rule;

/**
 * TODO description
 */
public class Home {
	
	public Home() {
		rules.add(new Rule("CLOCK_EQ_07:00", "TURN_OFF=All Inhouse@AUTOMATED_INHOUSE_ILLUMINATION"));
		rules.add(new Rule("CLOCK_EQ_19:00", "TURN_ON=All Inhouse@AUTOMATED_INHOUSE_ILLUMINATION"));
		rules.add(new Rule("CLOCK_EQ_23:00", "TURN_OFF=All Inhouse@AUTOMATED_INHOUSE_ILLUMINATION"));
	}

}