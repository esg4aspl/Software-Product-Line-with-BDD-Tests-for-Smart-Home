Feature: WindowsManagement-ManualWindows:3
Background: Setup
Given The environment is set up with "turn on,close windows manual"
When Feature name is "WindowsManagement-ManualWindows:3"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then close windows manual

