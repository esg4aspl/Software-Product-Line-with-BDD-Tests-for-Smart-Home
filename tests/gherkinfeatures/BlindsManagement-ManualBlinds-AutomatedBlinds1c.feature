Feature: BlindsManagement-ManualBlinds-AutomatedBlinds1
Background: Setup
Given The environment is set up with "turn on,output to console,open blinds automatic,output to console,close blinds automatic,output to console,close blinds manual,output to console,open blinds manual,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then open blinds automatic

Scenario: 2
Given turn on
And output to console
When open blinds automatic
Then output to console

Scenario: 3
Given turn on
And output to console
And open blinds automatic
When output to console
Then close blinds automatic

Scenario: 4
Given turn on
And output to console
And open blinds automatic
And output to console
When close blinds automatic
Then output to console

Scenario: 5
Given turn on
And output to console
And open blinds automatic
And output to console
And close blinds automatic
When output to console
Then close blinds manual

Scenario: 6
Given turn on
And output to console
And open blinds automatic
And output to console
And close blinds automatic
And output to console
When close blinds manual
Then output to console

Scenario: 7
Given turn on
And output to console
And open blinds automatic
And output to console
And close blinds automatic
And output to console
And close blinds manual
When output to console
Then open blinds manual

Scenario: 8
Given turn on
And output to console
And open blinds automatic
And output to console
And close blinds automatic
And output to console
And close blinds manual
And output to console
When open blinds manual
Then output to console

Scenario: 9
Given turn on
And output to console
And open blinds automatic
And output to console
And close blinds automatic
And output to console
And close blinds manual
And output to console
And open blinds manual
When output to console
Then turn off

