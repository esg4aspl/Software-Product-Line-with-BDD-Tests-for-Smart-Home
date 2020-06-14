Feature: UI-TouchScreen:13
Background: Setup
Given The environment is set up with "turn on,output to console,input via touchscreen,turn on"
When Feature name is "UI-TouchScreen:13"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then input via touchscreen

Scenario: 2
Given turn on
And output to console
When input via touchscreen
Then turn on

