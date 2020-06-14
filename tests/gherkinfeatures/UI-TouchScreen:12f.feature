Feature: UI-TouchScreen:12
Background: Setup
Given The environment is set up with "turn on,output to console,output via touchscreen"
When Feature name is "UI-TouchScreen:12"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then output via touchscreen

