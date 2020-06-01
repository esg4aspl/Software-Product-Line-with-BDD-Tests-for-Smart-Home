Feature: IrrigationSprinklers-ManualSprinklers-AutomatedSprinklers1
Background: Setup
Given The environment is set up with "turn on,output to console,turn on irrigation sprinklers automatic,output to console,turn off irrigation sprinklers automatic,output to console,turn off irrigation sprinklers manual,output to console,turn on irrigation sprinklers manual,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn on irrigation sprinklers automatic

Scenario: 2
Given turn on
And output to console
When turn on irrigation sprinklers automatic
Then output to console

Scenario: 3
Given turn on
And output to console
And turn on irrigation sprinklers automatic
When output to console
Then turn off irrigation sprinklers automatic

Scenario: 4
Given turn on
And output to console
And turn on irrigation sprinklers automatic
And output to console
When turn off irrigation sprinklers automatic
Then output to console

Scenario: 5
Given turn on
And output to console
And turn on irrigation sprinklers automatic
And output to console
And turn off irrigation sprinklers automatic
When output to console
Then turn off irrigation sprinklers manual

Scenario: 6
Given turn on
And output to console
And turn on irrigation sprinklers automatic
And output to console
And turn off irrigation sprinklers automatic
And output to console
When turn off irrigation sprinklers manual
Then output to console

Scenario: 7
Given turn on
And output to console
And turn on irrigation sprinklers automatic
And output to console
And turn off irrigation sprinklers automatic
And output to console
And turn off irrigation sprinklers manual
When output to console
Then turn on irrigation sprinklers manual

Scenario: 8
Given turn on
And output to console
And turn on irrigation sprinklers automatic
And output to console
And turn off irrigation sprinklers automatic
And output to console
And turn off irrigation sprinklers manual
And output to console
When turn on irrigation sprinklers manual
Then output to console

Scenario: 9
Given turn on
And output to console
And turn on irrigation sprinklers automatic
And output to console
And turn off irrigation sprinklers automatic
And output to console
And turn off irrigation sprinklers manual
And output to console
And turn on irrigation sprinklers manual
When output to console
Then turn off

