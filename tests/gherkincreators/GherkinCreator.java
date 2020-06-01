package gherkincreators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import testrunners.FeatureExtractor;

public class GherkinCreator {
	
	private String featureName;
	private List<String> testSequence;
	private Feature feature;
	
	public GherkinCreator(String featureName, String testSequence) {
		this(featureName, parseTestSequence(testSequence));
	}
	
	public GherkinCreator(String featureName, String[] testSequence) {
		this(featureName, Arrays.asList(testSequence));
	}
	
	public GherkinCreator(String featureName, List<String> testSequence) {
		super();
		this.featureName = featureName;
		this.testSequence = testSequence;
		
		String testSequenceString = "";
		
		
		int c = 0;
		int size = testSequence.size();
		for (String s : testSequence) {
			testSequenceString += s;
			if (c != size-1)
				testSequenceString += ",";
				
			c++;
		}
			
		
		
		List<Scenario> scenarios = new ArrayList<Scenario>();
		scenarios.add(new Scenario(true, "Setup", "The environment is set up with \"" + testSequenceString + "\"", null, null));
		System.out.println(testSequenceString);
		
		if (size > 2) {
			scenarios.add(new Scenario("0", null, "[", testSequence.get(0)));
			
			for (int origin = 0; origin < testSequence.size() - 2; origin++) {
				List<String> givens = new ArrayList<String>();
				for (int i = 0; i <= origin; i++) {
					givens.add(testSequence.get(i));
				}
				
				List<String> whens = new ArrayList<String>();
				whens.add(testSequence.get(origin+1));
				List<String> thens = new ArrayList<String>();
				thens.add(testSequence.get(origin+2));
				Scenario newScenario = new Scenario(String.valueOf(origin+1), givens, whens, thens);
				scenarios.add(newScenario);
			}			
		} else if (size > 1) {
			scenarios.add(new Scenario("0", null, "[", testSequence.get(0)));
			scenarios.add(new Scenario("1", null, testSequence.get(0), testSequence.get(1)));
		} else if (size > 0) {
			scenarios.add(new Scenario("0", null, "[", testSequence.get(0)));			
		}
		
		feature = new Feature(featureName, scenarios);
	}

	public String getFeatureName() {
		return featureName;
	}

	
	public List<String> getTestSequence() {
		return testSequence;
	}

	public Feature getFeature() {
		return feature;
	}
	
	private static List<String> parseTestSequence(String sequence) {
		List<String> result = new ArrayList<String>();
		String[] steps = sequence.strip().split(",");
		
		int decrement = 0;
		if (steps[steps.length-1].strip().equals("]"))
			decrement = 1;
		
		//Not counting [ and ]
		for (int i = 1; i < steps.length-decrement; i++) {
			result.add(steps[i].strip());
		}
		
		return result;
	}
	
	private static String[] getProductsByConfiguration(String configName) {
		List<String> features = new FeatureExtractor("SmartHome", "./configs/" + configName + ".xml").getFeatures();
		
		
		
		String[] allProducts = {
				"AVManagement-AutomatedAV",
				"BlindsManagement-ManualBlinds",
				"BlindsManagement-ManualBlinds-AutomatedBlinds",
				"Core",
//				"FireControl-FireDepartment",
//				"FireControl-FireDepartment-OtherGroup",
//				"FireControl-FireDepartment-FireSprinklers",
//				"FireControl-FireDepartment-OtherGroup-FireSprinklers",
				"IrrigationSprinklers-ManualSprinklers",
				"IrrigationSprinklers-ManualSprinklers-AutomatedSprinklers",
				"LightManagement-ManualIllumination",
				"LightManagement-ManualIllumination-AutomatedInhouseIllumination",
				"LightManagement-ManualIllumination-AutomatedInhouseIllumination-AutomatedPerimeterIllumination",
				"MoodsManagement-AutomatedMoods",
				"UI-TouchScreen",
				"UI-TouchScreen-Internet",
				"UI-TouchScreen-Internet-Privacy-RSA",
				"UI-TouchScreen-Internet-Privacy-DES",
				"WindowsManagement-ManualWindows",
				"WindowsManagement-ManualWindows-AutomatedWindows",
//				"Alarm-Bell",
//				"Alarm-Lights",
//				"Alarm-Siren",
//				"Alarm-Bell-Lights",
//				"Alarm-Bell-Siren",
//				"Alarm-Lights-Siren",
//				"Alarm-Bell-Lights-Siren",
		};
		
		List<String> productsList = new ArrayList<String>();
		
		for (String ap : allProducts) {
			String[] apFeatures = ap.split("-");
			boolean isIncluded = true;
			for (String apf : apFeatures) {
				if (!features.contains(apf)) {
					isIncluded = false;
					break;
				}
			}
			if (isIncluded) {
				productsList.add(ap);
			}
		}
		
		String[] products = productsList.toArray(new String[productsList.size()]);
		return products;
	}
	
	public static void main(String[] args) {
		
		String resultPrefix = "./tests/gherkinfeatures/";
		File directory = new File(resultPrefix);

		File[] files = directory.listFiles();
		for (File file : files)
		   if (!file.delete())
		       System.out.println("Failed to delete "+file);
		
		
		String[] products = getProductsByConfiguration("all");
		
		String type = "complete"; //complete or faulty
		String sourcePrefix = "./sequences/" + type + "/";
		
		for (String product : products) {
			String fileName = sourcePrefix + product + ".txt";
			try {
			      File myObj = new File(fileName);
			      Scanner myReader = new Scanner(myObj);
			      int lineCounter = 0;
			      while (myReader.hasNextLine()) {
						lineCounter++;
						String data = myReader.nextLine();
						GherkinCreator gc = new GherkinCreator(product + String.valueOf(lineCounter), data);
						
						String resultName = resultPrefix + product + String.valueOf(lineCounter) + String.valueOf(type.charAt(0)) + ".feature";
						
						try {
						    FileWriter myWriter = new FileWriter(resultName);
						    myWriter.write(gc.getFeature().toString());
						    myWriter.close();
						    System.out.println("Successfully wrote to the file.");
						} catch (IOException e) {
						    System.out.println("An error occurred.");
						    e.printStackTrace();
						}
			        
			        
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
		
		
		
		
	}
	
	
	
	

}
