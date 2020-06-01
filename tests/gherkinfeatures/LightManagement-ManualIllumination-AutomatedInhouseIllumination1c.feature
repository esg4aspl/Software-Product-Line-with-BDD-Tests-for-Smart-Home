Feature: LightManagement-ManualIllumination-AutomatedInhouseIllumination1
Background: Setup
Given The environment is set up with "turn on,output to console,turn on inhouse light automatic,output to console,turn off inhouse light automatic,output to console,turn on light manual,output to console,turn off light manual,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn on inhouse light automatic

Scenario: 2
Given turn on
And output to console
When turn on inhouse light automatic
Then output to console

Scenario: 3
Given turn on
And output to console
And turn on inhouse light automatic
When output to console
Then turn off inhouse light automatic

Scenario: 4
Given turn on
And output to console
And turn on inhouse light automatic
And output to console
When turn off inhouse light automatic
Then output to console

Scenario: 5
Given turn on
And output to console
And turn on inhouse light automatic
And output to console
And turn off inhouse light automatic
When output to console
Then turn on light manual

Scenario: 6
Given turn on
And output to console
And turn on inhouse light automatic
And output to console
And turn off inhouse light automatic
And output to console
When turn on light manual
Then output to console

Scenario: 7
Given turn on
And output to console
And turn on inhouse light automatic
And output to console
And turn off inhouse light automatic
And output to console
And turn on light manual
When output to console
Then turn off light manual

Scenario: 8
Given turn on
And output to console
And turn on inhouse light automatic
And output to console
And turn off inhouse light automatic
And output to console
And turn on light manual
And output to console
When turn off light manual
Then output to console

Scenario: 9
Given turn on
And output to console
And turn on inhouse light automatic
And output to console
And turn off inhouse light automatic
And output to console
And turn on light manual
And output to console
And turn off light manual
When output to console
Then turn off

