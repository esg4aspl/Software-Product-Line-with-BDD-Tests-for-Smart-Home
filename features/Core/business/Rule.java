package business;

import java.util.*;

/**
 * TODO description
 */
public class Rule {
	
	private static final String CONDITION_DIVIDER = "&&";
	private List<Condition> inputConditions;
	private Command outputCommand;
	
	public Rule(List<Condition> inputConditions, Command outputCommand) {
		this.inputConditions = inputConditions;
		this.outputCommand = outputCommand;
	}
	
	public Rule(String inputConditionsString, String outputCommandString) {
		this.inputConditions = new ArrayList<Condition>();
		String[] parts = inputConditionsString.split(CONDITION_DIVIDER);
		for (String p : parts) {
			this.inputConditions.add(new Condition(p));
		}
		this.outputCommand = new Command(outputCommandString);
	}
	
	public Rule(Condition[] inputConditions, Command outputCommand) {
		this.inputConditions = new ArrayList<Condition>();
		
		for (Condition c : inputConditions) {
			this.inputConditions.add(c);
		}
		
		this.outputCommand = outputCommand;
	}
	
	public Rule(String[] inputConditions, String outputCommandString) {
		this.inputConditions = new ArrayList<Condition>();
		
		for (String s : inputConditions) {
			this.inputConditions.add(new Condition(s));
		}
		
		this.outputCommand = new Command(outputCommandString);
	}
	
	public List<Condition> getInputConditions() {
		return inputConditions;
	}
	
	public Command getOutputCommand() {
		return outputCommand;
	}
	
	public String toString() {
		return "DO " + outputCommand.toString() + " WHEN " + inputConditions.toString();
	}
	
	public boolean match(List<Code> codes) {
		int matchCounter = 0;
		
		for (Condition c : inputConditions) {
			for (Code code : codes) {
				if (c.match(code)) {
					matchCounter++;
					continue;
				}
			}
		}
		
		if (matchCounter == inputConditions.size()) {
			return true;
		} else {
			return false;
		}
	}

}