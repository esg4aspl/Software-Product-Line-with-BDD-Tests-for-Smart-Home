Feature: WindowsManagement-ManualWindows:12
Background: Setup
Given The environment is set up with "turn on,output to console,close windows manual,turn on"
When Feature name is "WindowsManagement-ManualWindows:12"

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
Then turn on

