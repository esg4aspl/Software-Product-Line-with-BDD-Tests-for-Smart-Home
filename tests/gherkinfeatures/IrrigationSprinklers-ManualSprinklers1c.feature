Feature: IrrigationSprinklers-ManualSprinklers1
Background: Setup
Given The environment is set up with "turn on,output to console,turn off irrigation sprinklers manual,output to console,turn on irrigation sprinklers manual,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn off irrigation sprinklers manual

Scenario: 2
Given turn on
And output to console
When turn off irrigation sprinklers manual
Then output to console

Scenario: 3
Given turn on
And output to console
And turn off irrigation sprinklers manual
When output to console
Then turn on irrigation sprinklers manual

Scenario: 4
Given turn on
And output to console
And turn off irrigation sprinklers manual
And output to console
When turn on irrigation sprinklers manual
Then output to console

Scenario: 5
Given turn on
And output to console
And turn off irrigation sprinklers manual
And output to console
And turn on irrigation sprinklers manual
When output to console
Then turn off

