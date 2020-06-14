Feature: WindowsManagement-ManualWindows:19
Background: Setup
Given The environment is set up with "turn on,output to console,open windows manual,open windows manual"
When Feature name is "WindowsManagement-ManualWindows:19"

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
Then open windows manual

