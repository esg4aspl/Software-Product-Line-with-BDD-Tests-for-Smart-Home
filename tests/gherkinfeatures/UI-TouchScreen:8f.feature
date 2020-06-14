Feature: UI-TouchScreen:8
Background: Setup
Given The environment is set up with "turn on,output to console,turn off,input via touchscreen"
When Feature name is "UI-TouchScreen:8"

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
Then input via touchscreen

