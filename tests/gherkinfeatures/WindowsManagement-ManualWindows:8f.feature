Feature: WindowsManagement-ManualWindows:8
Background: Setup
Given The environment is set up with "turn on,output to console,turn off,close windows manual"
When Feature name is "WindowsManagement-ManualWindows:8"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn off

Scenario: 2
Given turn on
And output to console
When turn off
Then close windows manual

