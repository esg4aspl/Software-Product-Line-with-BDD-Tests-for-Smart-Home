package business;

/**
 * TODO description
 */
public class Code {
	
	public static final String DIVIDER = "=";
	private ECode eCode;
	private String data;
	
	public Code(ECode eCode) {
		this(eCode, "");
	}
	
	public Code(ECode eCode, String data) {
		this.eCode = eCode;
		this.data = data;
	}
	
	public Code(String commandString) {
		String[] parts = commandString.split(DIVIDER);
		this.eCode = ECode.parseECode(parts[0]);
		if (parts.length == 2) {
			this.data = parts[1];
		} else {
			this.data = "";
		}
	}
	
	public ECode getECode() {
		return eCode;
	}
	
	public String getData() {
		return data;
	}
	
	public String toString() {
		return eCode.toString() + DIVIDER + data;
	}
	
	@Override
    public boolean equals(Object o) {
        if (o == this) { 
            return true; 
        }
        if (!(o instanceof Code)) { 
            return false; 
        }
        Code c = (Code) o; 
        return eCode == c.getECode() && data.equals(c.getData()); 
    } 

}