Feature: UI-TouchScreen-Internet1
Background: Setup
Given The environment is set up with "turn on,output to console,input via Internet,output via Internet,output to console,input via touchscreen,output via touchscreen,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then input via Internet

Scenario: 2
Given turn on
And output to console
When input via Internet
Then output via Internet

Scenario: 3
Given turn on
And output to console
And input via Internet
When output via Internet
Then output to console

Scenario: 4
Given turn on
And output to console
And input via Internet
And output via Internet
When output to console
Then input via touchscreen

Scenario: 5
Given turn on
And output to console
And input via Internet
And output via Internet
And output to console
When input via touchscreen
Then output via touchscreen

Scenario: 6
Given turn on
And output to console
And input via Internet
And output via Internet
And output to console
And input via touchscreen
When output via touchscreen
Then output to console

Scenario: 7
Given turn on
And output to console
And input via Internet
And output via Internet
And output to console
And input via touchscreen
And output via touchscreen
When output to console
Then turn off

