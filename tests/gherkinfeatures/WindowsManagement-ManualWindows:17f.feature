Feature: WindowsManagement-ManualWindows:17
Background: Setup
Given The environment is set up with "turn on,output to console,open windows manual,turn off"
When Feature name is "WindowsManagement-ManualWindows:17"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then open windows manual

Scenario: 2
Given turn on
And output to console
When open windows manual
Then turn off

