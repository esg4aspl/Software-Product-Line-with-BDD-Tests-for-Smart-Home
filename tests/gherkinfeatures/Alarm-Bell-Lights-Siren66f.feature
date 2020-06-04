Feature: Alarm-Bell-Lights-Siren66
Background: Setup
Given The environment is set up with "turn on,output to console,turn off bell,turn on bell"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn off bell

Scenario: 2
Given turn on
And output to console
When turn off bell
Then turn on bell

