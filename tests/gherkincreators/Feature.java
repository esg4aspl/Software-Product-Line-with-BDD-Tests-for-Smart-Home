package gherkincreators;

import java.util.List;

public class Feature {
	
	private String name;
	private List<Scenario> scenarios;
	
	public Feature(String name, List<Scenario> scenarios) {
		super();
		this.name = name;
		this.scenarios = scenarios;
	}

	public String getName() {
		return name;
	}

	public List<Scenario> getScenarios() {
		return scenarios;
	}
	
	public int getScenarioCount() {
		return scenarios.size();
	}
	
	public int getStepCount() {
		int stepCount = 0;
		for (Scenario s : scenarios) {
			stepCount += s.getGivens().size();
			stepCount += s.getWhens().size();
			stepCount += s.getThens().size();
		}
		return stepCount;
	}

	@Override
	public String toString() {
		String result = "Feature: " + name + "\n";
		for (Scenario s : scenarios)
			result += s.toString() + "\n";
		return result;
	}
	
}
