Feature: WindowsManagement-ManualWindows:4
Background: Setup
Given The environment is set up with "turn on,open windows manual"
When Feature name is "WindowsManagement-ManualWindows:4"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then open windows manual

