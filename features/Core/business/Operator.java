package business;

/**
 * TODO description
 */
public enum Operator {
	
	_LT_, //Less Than
	_MT_, //More Than
	_NE_, //Not Equals
	_EQ_; //Equals
	
	public static Operator parseOperator(String eOperatorString) {
		Operator[] allOperators = Operator.values();
		for (Operator o : allOperators)
			if (o.toString().equals(eOperatorString))
				return o;
		
		return _EQ_;
	}

}