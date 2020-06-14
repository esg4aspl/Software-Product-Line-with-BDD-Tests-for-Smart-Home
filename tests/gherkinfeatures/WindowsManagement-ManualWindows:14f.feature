Feature: WindowsManagement-ManualWindows:14
Background: Setup
Given The environment is set up with "turn on,output to console,close windows manual,close windows manual"
When Feature name is "WindowsManagement-ManualWindows:14"

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
Then close windows manual

