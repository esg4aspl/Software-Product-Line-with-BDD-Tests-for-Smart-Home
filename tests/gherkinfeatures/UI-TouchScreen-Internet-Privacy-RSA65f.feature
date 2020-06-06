Feature: UI-TouchScreen-Internet-Privacy-RSA65
Background: Setup
Given The environment is set up with "turn on,output to console,input via touchscreen,output via touchscreen,turn off"

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
Then output via touchscreen

Scenario: 3
Given turn on
And output to console
And input via touchscreen
When output via touchscreen
Then turn off

