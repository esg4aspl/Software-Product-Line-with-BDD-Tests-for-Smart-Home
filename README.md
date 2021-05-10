# Software Product Line with BDD Tests for Smart Home

# Environment
This project is developed in Eclipse IDE, using its FeatureIDE plugin. It also uses Redis for pub/sub which is required to be running on the machine.

# FeatureIDE and FeatureHouse
FeatureIDE comes bundled with multiple composers. In this work, we used the FeatureHouse composer. The official website of FeatureHouse describes the composer as: "FeatureHouse is a general approach to the composition of software artifacts. FeatureHouse is language-independent in that software artifacts written in various languages can be composed, e.g., source code, test cases, models, documentation, and makefiles. Software artifacts are represented as feature structure trees (FSTs), which capture the essence of an artifact's modular structure in the form of a tree. As composition paradigm, FeatureHouse supports two approaches: superimposition and three-way merge."  When a new product configuration is selected or one of the software artifacts is edited, FeatureHouse re-composes the source files and creates one single final version of every software artifact. This makes it possible to create a new product of the software product line with few clicks.  
  
Classes are created under 'features' folder, inside their respective folder names. In this work, we had many different features and each feature was considered as "subsystems" of a SmartHome, thus each feature created a new class that represented its subsystem. And in each feature, that subsystem's parent system was overridden to include the subsystem.  

One distinction is between FeatureIDE source files and FeatureHouse composed source files. We can think of the former as blueprints and the latter as the composition of those blueprints. Upon saving the FeatureIDE source files (blueprints) which are in essence Java classes, FeatureHouse composes and compiles the Java source files (composition) in "src" directory. It only uses the blueprints that are associated with the features selected in product configuration XML file. Then, the Cucumber tests are run on the final composed source files.

We created a tool that handles two functions of the development cycle: converting test sequences to Gherkin feature files and customizing Cucumber test results into a format that makes sense in our approach. Gherkin Feature File Generator (SPL-AT-GFFG) of the SPL-AT Tool uses an algorithm we developed, which is explained in the next section, takes a set of test sequences as input and creates Gherkin feature files. We created Test Result Report Generator (SPL-AT-TRRG) to format Cucumber test results into a more presentable report that would make sense in FESG approach. SPL-AT-TRRG is explained in more detail later in this file.


# GherkinCreator
We have prepared an algorithm to automatically generate Gherkin feature files from the test sequences that were created from the model. The idea was to make every sequence of length 3 testable when placed in 'Given', 'When', 'Then' steps of a Gherkin scenario, respectively. However, in this approach, the 'Given' step is not always guaranteed to be the first event of its CES. In that case, the test would have started from an arbitrary position in the CES and possibly fail. To solve this issue, we have incrementally added the each created scenario (from a sequence of length 3) to its predecessor. In Gherkin approach, this meant that for a scenario that starts with the nth event in the CES, 1st through (n – 1)th events must be placed in preceding 'Given' steps.
The following is an example for a CES of length 5. 5: [, event1, event2, event3, event4, event5, ], Scenario: 1
Given event1 When event2 Then event3
Scenario: 2 Given event1 And event2
When event3 Then event4
Scenario: 3 Given event1 And event2 And event3 When event4 Then event5
Next consideration in the algorithm was that event1 was never in the 'Then' step. For that, we added a special clause for the creation of the following scenario:
Scenario: 0 When [ Then event1
Next consideration in the algorithm was for sequences of length 1 and sequences of length 2. The special clause explained in the above paragraph is applied for sequences of length 1. The following example shows how the scenarios are created for sequences of length 2.
2: [, event1, event2, ],
Scenario: 0 When [ Then event1
Scenario: 1 When event1 Then event2
Pseudocode for the algorithm: Input format = "[, event1, event2, event3, event4, event5, ],"
```
feature;
events[] = split(input, ","); //disregard [ and ]
numberOfEvents = events.length;
feature.addScenario(Name:"0", When:"[", Then:event1);
if (numberOfEvents == 2) {
  feature.addScenario(Name:"1", When:event1, Then:event2);
} else if (numberOfEvents > 2) {
  for (origin = 0; origin < numberOfEvents-2; origin++) {
    givens[];
    for (i = 0; i <= origin; i++) {
      givens.add(events[i]);
    }
    feature.addScenario(Name:origin+1, Given:givens, When:events[origin+1], Then:events[origin+2]);
  }
}
writeToFile(feature);
```
This algorithm required us to slightly vary the step implementations from the non-official Gherkin rules; since one event can be in 'Given', 'When', and 'Then' steps, it does not comply with Gherkin's assumptions, actions, and outcomes approach for respective steps. In our approach, a step implementation comprises all three types of these method calls.
In a relatively big project, there is a need of an algorithm that picks the features to be tested based on the product configuration. The algorithm takes two inputs: The list of all test sequence feature combinations and the product configuration xml file. It runs in two iterations: First it iterates through every test sequence name and removes the test sequences if one of their features is not in the product configuration. For instance, if the RSA encryption is not in the product, then that test sequence should be removed, and vice versa. After the second iteration, the final list of test sequences will be converted to Gherkin feature files using SPL-AT GFFG.
One more filtering option that comes up in complex systems are the "edge cases". Suppose one feature is hierarchically below another in the model; and these two features implement the same functionality in different ways. If that is the case, existence of one should discard the other in terms of testing. For example, the presence of an encryption algorithm (which is optional) changes the implementation of the unencrypted Internet feature algorithm so much that some valid event sequences of unencrypted Internet are not valid sequences in an encrypted Internet environment. However the exclusivity cannot be detected using feature names because hierarchically one is underneath the other. In this case, it is up to the tester to understand the situation and add the edge cases manually.

# Implementation
In order to create the SmartHome program we have used a pub/sub architecture with the help of Redis and its Java implementation: Jedis [ref]. In our architecture, every component of the SmartHome is considered to be a "system", including the Home class itself which is the parent of all systems. Each system has its own systems connected to it (indicated as subsystems) so it is very easy and independent to just plug in and plug out systems without affecting any other part of the SmartHome. In order to see how the subsystem architecture fits together, we can examine the composed version of the Home class and its subclasses (in a product configuration that has TouchScreen, ManualIllumination, ManualWindows, ManualHeating, AutomatedHeating and RemoteHeating) :
The Home class, parent system of all other systems, is composed as follows:
```
public Home () {
  super();
}
rules = new ArrayList<Rule>();
RuleReader ruleReader = new RuleReader();
ruleMap = ruleReader.getMap();
started = false;
turnedOff = false;
this.subsystems.add(new UI(this));
this.subsystems.add(new LightManagement(this));
this.subsystems.add(new WindowsManagement(this));
this.subsystems.add(new HeatingControl(this));
List<Rule> automatedHeatingRules = ruleMap.get(Channel.AUTOMATED_HEATING);
if (automatedHeatingRules != null)
   rules.addAll(automatedHeatingRules);
```
In the constructor method above, the subsystems that the Home directly knows are added to its subsystems list. These additions are not written by the programmer in one single place. For example,
```
this.subsystems.add(new LightManagement(this));
```
is actually embedded in Home class's blueprint inside the LightManagement feature folder. Similarly, automated heating rules are not added to Home's rules by HeatingControl, they are added by AutomatedHeating only if it's present in product configuration.
Further exploring this example, we can understand the plug-in/plug-out structure. Home only knows HeatingControl as its subsystem. Every other smaller subsystems related to heating are only known by HeatingControl class:
```
public HeatingControl (ISystem parentSystem) {
  super(parentSystem);
  this.subsystems.add(new ManualHeating(this));
  this.subsystems.add(new AutomatedHeating(this));
  this.subsystems.add(new RemoteHeatingControl(this));
}
```
Here we can see that the HeatingControl is added three subsystems that are present in product configuration. Any subsystem can always act as a leaf as well and respond to commands, or they can be designed as containers only. We chose to write the small subsystems as bridges that only transfer the messages given to them. For example, when the home owner tries to increase the temperature via touchscreen, this message is first published to the MANUAL_HEATING channel via Redis. Upon catching this message, ManualHeating class then forwards it to the HeatingControl where the logic is implemented. For the scope of this research, we have only produced console messages as responses, however in a real world application this could be the place where HeatingControl subsystem gives power to the radiators around the house to increase temperature. Similarly, messages sent to AutomatedHeating and RemoteHeatingControl are forwarded to HeatingControl where the logic lays.  
  
All systems define a method called "respond" which accepts a Command and produces the necessary output. They listen to the commands that are sent to their own channel. Systems can be viewed in two different types: manually triggered systems, and environment-triggered systems (automated systems). We assumed that the house was donated with multiple sensors and constantly updating input sources like clock, digital thermometer, smoke detectors etc., and these input sources always fed the Home with data for every time frame (such as every second/every 100 millisecond). In each time frame, Home reads all the inputs and compares them with the user-generated automation rules and produces a command and sends it to the appropriate Redis channel, thus the appropriate subsystem and the subsystem produces the desired output. This automation system allowed us to create rules that are similar to the English language:
```
[AUTOMATED_AIR_CONDITIONING_CONTROL]
if TEMPERATURE>30 then TURN_ON=All
if TEMPERATURE<23 then TURN_OFF=All
```
These statements mean that for AutomatedAirConditioningControl, the user wants to open the AC when temperature rises above 30, and close it when temperature drops below 23. The "All" part is the extra details, where instead it could be "Kitchen" and the AC automation rule would only work for the kitchen in the house.

# Folder Structure
The Smart Home model and its features are initially designed in the **model.xml** file.

- **src** package is where FeatureHouse compiles the blueprints that are specified in **features** package. It is empty until the code is composed by FeatureHouse. 
  - **features** package contains the folders that are automatically created for every feature defined in model.xml file. Every feature makes its own changes or additions to related classes, or creates new classes that will be used in composition by FeatureHouse. The "Core" folder defines the system in its most basic form, with no features attached. And every other feature adds its logic on top of it.
  - It also contains the **stepdefinitions** package because test step definitions are also compiled by FeatureHouse (will be explained in more detail).

- **tests** package contains the classes that are related to generation of Gherkin feature files and report creation. It also contains TestRunner class which runs (run as JUnit) the tests generated (by GherkinCreator) in the **gherkinfeatures** file using step definitions compiled by FeatureIDE into **src**. Finally it contains GeneralAutomator class that we created in a late commit which automates the process of "Generate Features - Run Tests - Generate Report" steps. Its usage is explained in the last section.

- **configs** folder is where product configuration XML files are created. For this project, 20 product configurations are created with an increasing number of features where Product01 contains only the core features and Product20 contains all features.

- **sequences** folder contains the complete and faulty event sequences that the tests will be based on.

- **settings** folder has two txt files: commands are the actions that the home owner can trigger from the UI, and rules are the automation rules described in the previous paragraph.

# Step Definitions
The step definitions of the Gherkin feature files are revolved around Redis messages, conforming with the SmartHome implementation. It sends out a Command based on what is currently being tested, and checks the outputs produced by either the Home or one of its subsystems. An action is defined as an event that triggers some response in the Home, such as home owner attempting to manually turn on the lights from touchscreen UI or a fire breaking out because of a lightning. And the events are defined as the responses of the Home, such as giving power to the lights that the home owner requested, or automatically calling the fire department. For every action, a message is sent to the corresponding channel (of subsystem, such as LightManagement or FireDepartment) and the response is checked with the "output to console" event.

If not broken down properly, StepDefinitions class tends to get very big in a short time while implementing the test steps in big projects. It contains logic for multiple features that have nothing in common, and makes it hard to understand, improve or debug. In order to avoid that while writing the StepDefinitions for SmartHome, we used FeatureHouse and wrote in it in a per-feature fashion just like the source code. Every FeatureHouse feature folder would not only define the component's implementation, but also the implementation of the test steps that it is related to. This allowed us to have many small testing components that are easy to understand and debug. These testing components are composed together by FeatureHouse to create one final source of StepDefinitions class that can be run by Cucumber. The developer never directly interacts with the composed, clustered version.

# ReportCreator
Normally, Gherkin tests are evaluated around scenarios, and test result files do not respect whether the Gherkin feature passed as a whole or not. However we were more interested in the evaluation of Gherkin features, because essentially the information we wanted to know is if a test sequence passed or not, and SPL-AT-GFFG relates test sequences to Gherkin features. So we created SPL-AT-TRRG that takes the Cucumber's test result json file as input and creates a report from the point of view of test sequences.
The reports generated by SPL-AT-TRRG presents the Cucumber test results in four columns: Gherkin feature file name, status (failed/passed), transition where the error occurred (event(n) → event(n+1)), and error location (Scenario x, Step y). Like explained in the above paragraph, what shown as failed or passed are not only the scenarios, but also the whole features, which is exactly what we need to know. The most important column is the transition column, which tells us that the (n+1)th event can not come after the nth event, and that's what caused the error.

# How to Run?
To run tests on a product, you have to right click on a Product configuration (in **configs** folder) -> select "FeatureIde" -> select "Set as Current Configuration".

After you select the configuration, make sure that the code is automatically compiled into **src**. Sometimes FeatureIDE does not compile the new configuration selection, in that case it helps to delete the contents of **src** and select configuration again.

After the code is compiled, open GeneralAutomator under **tests/testrunners**, and move to the main method. Write the product name that you set as current configuration, and write "complete" or "faulty" as the sequence type to run their respective sequences. Then run the class as Java Application. The results will be printed upon completion.