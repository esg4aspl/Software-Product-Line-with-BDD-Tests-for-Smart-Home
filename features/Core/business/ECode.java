package business;

/**
 * TODO description
 */
public enum ECode {
	
	//DEVICES
	TURN_ON,
	TURN_OFF,
	SET_DEGREE,
	AUTOMATE, //true/false
	
	//ENVIRONMENT
	AIR,
	TEMPERATURE,
	PRESENCE,
	CLOCK,
	GLASSBREAK,
	
	UNIDENTIFIED_CODE;
	
	public static ECode parseECode(String codeString) {
		ECode[] allCodes = ECode.values();
		for (ECode c : allCodes)
			if (c.toString().equals(codeString))
				return c;
		
		return UNIDENTIFIED_CODE;
	}

}