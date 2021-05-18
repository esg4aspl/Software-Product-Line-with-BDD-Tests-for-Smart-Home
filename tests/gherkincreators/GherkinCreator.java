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
		List<String> backgroundGivens = new ArrayList<String>();
		backgroundGivens.add("The environment is set up with \"" + testSequenceString + "\"");
		backgroundGivens.add("Feature name is \"" + featureName + "\"");
		scenarios.add(new Scenario(true, "Setup", backgroundGivens, new ArrayList<String>(), new ArrayList<String>()));
//		System.out.println(testSequenceString);
		
		if (size > 2) {
			scenarios.add(new Scenario("0", null, testSequence.get(0), testSequence.get(1)));
			
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
		String[] steps = sequence.trim().split(",");
		
		int decrement = 0;
		if (steps[steps.length-1].trim().equals("]"))
			decrement = 1;
		
		//Not counting [ and ]
		for (int i = 1; i < steps.length-decrement; i++) {
			result.add(steps[i].trim());
		}
		
		return result;
	}
	
	private static String[] getProductsByConfiguration(String configName) {
		List<String> features = new FeatureExtractor("SmartHome", "./configs/" + configName + ".xml").getFeatures();
		
		
		String[] allProducts = {
				"AVManagement-AutomatedAV",
				"Alarm-Bell",
				"Alarm-Bell-Lights",
				"Alarm-Bell-Lights-Siren",
				"Alarm-Bell-Siren",
				"Alarm-Lights",
				"Alarm-Lights-Siren",
				"Alarm-Siren",
				"BlindsManagement-ManualBlinds",
				"BlindsManagement-ManualBlinds-AutomatedBlinds",
				"Core",
				"FireControl-FireDepartment",
				"FireControl-FireDepartment-FireSprinklers",
				"FireControl-FireDepartment-OtherGroup",
				"FireControl-FireDepartment-OtherGroup-FireSprinklers",
				"HVACManagement-AirConditioningControl-ManualAirConditioningControl",
				"HVACManagement-AirConditioningControl-ManualAirConditioningControl-AutomatedAirConditioningControl",
				"HVACManagement-AirConditioningControl-ManualAirConditioningControl-AutomatedAirConditioningControl-RemoteAirConditioningControl",
				"HVACManagement-HeatingControl-ManualHeating",
				"HVACManagement-HeatingControl-ManualHeating-AutomatedHeating",
				"HVACManagement-HeatingControl-ManualHeating-AutomatedHeating-RemoteHeatingControl",
				"HVACManagement-VentilationControl-ManualVentilationControl",
				"HVACManagement-VentilationControl-ManualVentilationControl-AutomatedVentilationControl",
				"HVACManagement-VentilationControl-ManualVentilationControl-AutomatedVentilationControl-RemoteVentilationControl",
				"IrrigationSprinklers-ManualSprinklers",
				"IrrigationSprinklers-ManualSprinklers-AutomatedSprinklers",
				"LightManagement-ManualIllumination",
				"LightManagement-ManualIllumination-AutomatedInhouseIllumination",
				"LightManagement-ManualIllumination-AutomatedInhouseIllumination-AutomatedPerimeterIllumination",
				"MoodsManagement-AutomatedMoods",
				"PresenceSimulation-AVSimulation",
				"PresenceSimulation-BlindsSimulation",
				"PresenceSimulation-BlindsSimulation-AVSimulation",
				"PresenceSimulation-LightSimulation",
				"PresenceSimulation-LightSimulation-AVSimulation",
				"PresenceSimulation-LightSimulation-BlindsSimulation",
				"PresenceSimulation-LightSimulation-BlindsSimulation-AVSimulation",
				"Security-AuthenticationDevice-FingerprintReader",
				"Security-AuthenticationDevice-FingerprintReader-IntrusionDetectionDevice-Cameras",
				"Security-AuthenticationDevice-FingerprintReader-IntrusionDetectionDevice-Cameras-GlassbreakSensors",
				"Security-AuthenticationDevice-FingerprintReader-IntrusionDetectionDevice-GlassbreakSensors",
				"Security-AuthenticationDevice-FingerprintReader-RetinaScanner",
				"Security-AuthenticationDevice-FingerprintReader-RetinaScanner-IntrusionDetectionDevice-Cameras",
				"Security-AuthenticationDevice-FingerprintReader-RetinaScanner-IntrusionDetectionDevice-Cameras-GlassbreakSensors",
				"Security-AuthenticationDevice-FingerprintReader-RetinaScanner-IntrusionDetectionDevice-GlassbreakSensors",
				"Security-AuthenticationDevice-Keypad",
				"Security-AuthenticationDevice-Keypad-FingerprintReader-RetinaScanner",
				"Security-AuthenticationDevice-Keypad-FingerprintReader-RetinaScanner-IntrusionDetectionDevice-Cameras",
				"Security-AuthenticationDevice-Keypad-FingerprintReader-RetinaScanner-IntrusionDetectionDevice-Cameras-GlassbreakSensors",
				"Security-AuthenticationDevice-Keypad-FingerprintReader-RetinaScanner-IntrusionDetectionDevice-GlassbreakSensors",
				"Security-AuthenticationDevice-Keypad-IntrusionDetectionDevice-Cameras",
				"Security-AuthenticationDevice-Keypad-IntrusionDetectionDevice-Cameras-GlassbreakSensors",
				"Security-AuthenticationDevice-Keypad-IntrusionDetectionDevice-GlassbreakSensors",
				"Security-AuthenticationDevice-Keypad-RetinaScanner",
				"Security-AuthenticationDevice-Keypad-RetinaScanner-IntrusionDetectionDevice-Cameras",
				"Security-AuthenticationDevice-Keypad-RetinaScanner-IntrusionDetectionDevice-Cameras-GlassbreakSensors",
				"Security-AuthenticationDevice-Keypad-RetinaScanner-IntrusionDetectionDevice-GlassbreakSensors",
				"Security-AuthenticationDevice-RetinaScanner",
				"Security-AuthenticationDevice-RetinaScanner-IntrusionDetectionDevice-Cameras",
				"Security-AuthenticationDevice-RetinaScanner-IntrusionDetectionDevice-Cameras-GlassbreakSensors",
				"Security-AuthenticationDevice-RetinaScanner-IntrusionDetectionDevice-GlassbreakSensors",
				"UI-TouchScreen",
				"UI-TouchScreen-Internet",
				"UI-TouchScreen-Internet-Privacy-DES",
				"UI-TouchScreen-Internet-Privacy-RSA",
				"WindowsManagement-ManualWindows",
				"WindowsManagement-ManualWindows-AutomatedWindows",
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
		
		if (features.contains("Privacy")) {
			productsList.remove("UI-TouchScreen-Internet");
		}
		
		if (features.contains("OtherGroup") && !features.contains("FireSprinklers")) {
			productsList.remove("FireControl-FireDepartment");
			productsList.remove("FireControl-FireDepartment-FireSprinklers");
		} else if (!features.contains("OtherGroup") && features.contains("FireSprinklers")) {
			productsList.remove("FireControl-FireDepartment");
			productsList.remove("FireControl-FireDepartment-OtherGroup");
		} else if (features.contains("OtherGroup") && features.contains("FireSprinklers")) {
			productsList.remove("FireControl-FireDepartment");
			productsList.remove("FireControl-FireDepartment-OtherGroup");
			productsList.remove("FireControl-FireDepartment-FireSprinklers");
		}
		
		String[] products = productsList.toArray(new String[productsList.size()]);
		return products;
	}

	//Product name -> "Product01", "Product02" etc.
	//Sequence type -> "complete" or "faulty"
	public static long automate(String productName, String sequenceType) {
		long startTime = System.nanoTime();

		String resultPrefix = "./tests/gherkinfeatures/";
		File directory = new File(resultPrefix);
		if (!directory.exists()){
			directory.mkdirs();
		}
		File[] files = directory.listFiles();
		for (File file : files)
		   if (!file.delete())
		       System.out.println("Failed to delete "+file);
		
		
		String[] products = getProductsByConfiguration(productName);
		 
		String sourcePrefix = "./sequences/" + sequenceType + "/";
		
		int featureCount = 0;
		int scenarioCount = 0;
		int stepCount = 0;
		for (String product : products) {
			String fileName = sourcePrefix + product + ".txt";
			try {
			      File myObj = new File(fileName);
			      Scanner myReader = new Scanner(myObj);
			      int lineCounter = 0;
			      while (myReader.hasNextLine()) {
						lineCounter++;
						String data = myReader.nextLine();
						GherkinCreator gc = new GherkinCreator(product + ":" +  String.valueOf(lineCounter), data);
						
						String resultName = resultPrefix + product + ":" + String.valueOf(lineCounter) + String.valueOf(sequenceType.charAt(0)) + ".feature";
						
						try {
						    FileWriter myWriter = new FileWriter(resultName);
						    myWriter.write(gc.getFeature().toString());
						    myWriter.close();
//						    System.out.println("Successfully wrote to the file.");
						} catch (IOException e) {
						    System.out.println("An error occurred.");
						    e.printStackTrace();
						}
			        
						featureCount++;
						scenarioCount += gc.getFeature().getScenarioCount();
						stepCount += gc.getFeature().getStepCount();
						
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
		
		long endTime = System.nanoTime();

		// get difference of two nanoTime values
		long timeElapsedMilliseconds = (endTime - startTime) / 1000000;
		return timeElapsedMilliseconds;
		
//		System.out.println("Gherkin feature files are created in ./tests/gherkinfeatures. Now run the TestRunner and after it finishes run the ReportCreator to see the results of your tests.");
//		System.out.println("Feature creation execution time in milliseconds : " + timeElapsedMilliseconds);
		
	}
	
	public static void main(String[] args) {
		String[] productNames = {
				"Product01",
				"Product02",
				"Product03",
				"Product04",
				"Product05",
				"Product06",
				"Product07",
				"Product08",
				"Product09",
				"Product10",
				"Product11",
				"Product12",
				"Product13",
				"Product14",
				"Product15",
				"Product16",
				"Product17",
				"Product18",
				"Product19",
				"Product20"
		};
		
		for (String x : productNames) {
			GherkinCreator.automate(x, "complete");
		}
	}
	
	
	
	

}
