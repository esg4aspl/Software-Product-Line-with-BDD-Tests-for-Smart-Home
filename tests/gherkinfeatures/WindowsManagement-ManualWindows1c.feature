Feature: WindowsManagement-ManualWindows1
Background: Setup
Given The environment is set up with "turn on,output to console,close windows manual,output to console,open windows manual,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then close windows manual

Scenario: 2
Given turn on
And output to console
When close windows manual
Then output to console

Scenario: 3
Given turn on
And output to console
And close windows manual
When output to console
Then open windows manual

Scenario: 4
Given turn on
And output to console
And close windows manual
And output to console
When open windows manual
Then output to console

Scenario: 5
Given turn on
And output to console
And close windows manual
And output to console
And open windows manual
When output to console
Then turn off

