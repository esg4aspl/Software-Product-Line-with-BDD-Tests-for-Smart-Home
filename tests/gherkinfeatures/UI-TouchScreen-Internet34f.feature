Feature: UI-TouchScreen-Internet34
Background: Setup
Given The environment is set up with "turn on,output to console,input via touchscreen,output via Internet"

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
Then output via Internet

