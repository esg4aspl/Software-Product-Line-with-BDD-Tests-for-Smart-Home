Feature: WindowsManagement-ManualWindows-AutomatedWindows1
Background: Setup
Given The environment is set up with "turn on,output to console,open windows automatic,output to console,close windows automatic,output to console,close windows manual,output to console,open windows manual,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then open windows automatic

Scenario: 2
Given turn on
And output to console
When open windows automatic
Then output to console

Scenario: 3
Given turn on
And output to console
And open windows automatic
When output to console
Then close windows automatic

Scenario: 4
Given turn on
And output to console
And open windows automatic
And output to console
When close windows automatic
Then output to console

Scenario: 5
Given turn on
And output to console
And open windows automatic
And output to console
And close windows automatic
When output to console
Then close windows manual

Scenario: 6
Given turn on
And output to console
And open windows automatic
And output to console
And close windows automatic
And output to console
When close windows manual
Then output to console

Scenario: 7
Given turn on
And output to console
And open windows automatic
And output to console
And close windows automatic
And output to console
And close windows manual
When output to console
Then open windows manual

Scenario: 8
Given turn on
And output to console
And open windows automatic
And output to console
And close windows automatic
And output to console
And close windows manual
And output to console
When open windows manual
Then output to console

Scenario: 9
Given turn on
And output to console
And open windows automatic
And output to console
And close windows automatic
And output to console
And close windows manual
And output to console
And open windows manual
When output to console
Then turn off

