package business;

/**
 * TODO description
 */
public class Condition {
	
	private ECode eCode;
	private Operator operator;
	private String data;
	
	public Condition(ECode eCode, Operator operator, String data) {
		this.eCode = eCode;
		this.operator = operator;
		this.data = data;
	}
	
	public Condition(String conditionString) {
		String[] parts;
		
		if (conditionString.contains(Operator._NE_.toString())) {
			parts = conditionString.split(Operator._NE_.toString());
			this.operator = Operator._NE_;
		} else if (conditionString.contains(Operator._LT_.toString())) {
			parts = conditionString.split(Operator._LT_.toString());
			this.operator = Operator._LT_;
		} else if (conditionString.contains(Operator._MT_.toString())) {
			parts = conditionString.split(Operator._MT_.toString());
			this.operator = Operator._MT_;
		} else {
			parts = conditionString.split(Operator._EQ_.toString());
			this.operator = Operator._EQ_;
		}
		
		this.eCode = ECode.parseECode(parts[0]);
		this.data = parts[1];
	}
	
	public String toString() {
		return eCode.toString() + operator.toString() + data;
	}
	
	public boolean match(Code code) {
		if (code.getECode() != eCode)
			return false;
		
		if (operator == Operator._NE_) {
			return !code.getData().equals(data);
		} else if (operator == Operator._LT_) {
			return code.getData().compareTo(data) < 0;
		} else if (operator == Operator._MT_) {
			return code.getData().compareTo(data) > 0;
		} else {
			return code.getData().equals(data);
		}
	}
	

}