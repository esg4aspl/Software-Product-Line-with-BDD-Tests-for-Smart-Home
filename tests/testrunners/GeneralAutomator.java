package testrunners;





import java.io.File;
import java.io.IOException;

import cucumber.api.cli.Main;
import gherkincreators.GherkinCreator;
import reportcreator.ReportCreator;

public class GeneralAutomator {
	
	public static void run(String productName, String sequenceType) {
		long startTime = System.nanoTime();
		
		File resultsFile = new File("./target/cuke-results.json");
		try {
			resultsFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		long gherkinCreationTime = GherkinCreator.automate(productName, sequenceType);
		
		String [] argv = new String[]{"-g", "stepdefinitions/", "./tests/gherkinfeatures/", "-p", "json:target/cuke-results.json"};
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		byte exitstatus = Main.run(argv, contextClassLoader);
		
		long reportCreationTime = ReportCreator.automate();
		
		long endTime = System.nanoTime();
		long totalTime = (endTime - startTime) / 1000000;
		
		long cucumberTime = totalTime - gherkinCreationTime - reportCreationTime;
		System.out.println();
		System.out.println("Tests created and run for " + productName + " " + sequenceType + " sequences. You can view the results at target/customized-results.txt");
		System.out.println("Feature creation time: \t" + gherkinCreationTime + " ms\n" +
							"Cucumber test time: \t" + cucumberTime + " ms\n" +
							"Report creation time: \t" + reportCreationTime + " ms\n" +
							"Total time: \t\t" + totalTime + " ms");
		

	}
	
	public static void main(String[] args) {
		GeneralAutomator.run("Product01", "complete");
	}

}
