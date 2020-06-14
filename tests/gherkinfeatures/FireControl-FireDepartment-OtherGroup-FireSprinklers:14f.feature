Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:14
Background: Setup
Given The environment is set up with "turn on,output to console,turn off,open fire sprinklers"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:14"

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
Then open fire sprinklers

