package business;

/**
 * TODO description
 */
public enum Code {
	
	UNIDENTIFIED_CODE;
	
	public static Code parseCode(String codeString) {
		Code[] allCodes = Code.values();
		for (Code c : allCodes)
			if (c.toString().equals(codeString))
				return c;
		
		return UNIDENTIFIED_CODE;
	}

}