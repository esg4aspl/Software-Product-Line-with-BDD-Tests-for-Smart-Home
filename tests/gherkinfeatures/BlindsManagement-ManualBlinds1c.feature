Feature: BlindsManagement-ManualBlinds1
Background: Setup
Given The environment is set up with "turn on,output to console,close blinds manual,output to console,open blinds manual,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then close blinds manual

Scenario: 2
Given turn on
And output to console
When close blinds manual
Then output to console

Scenario: 3
Given turn on
And output to console
And close blinds manual
When output to console
Then open blinds manual

Scenario: 4
Given turn on
And output to console
And close blinds manual
And output to console
When open blinds manual
Then output to console

Scenario: 5
Given turn on
And output to console
And close blinds manual
And output to console
And open blinds manual
When output to console
Then turn off

