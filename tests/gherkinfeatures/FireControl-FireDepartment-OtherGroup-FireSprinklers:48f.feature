Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:48
Background: Setup
Given The environment is set up with "turn on,output to console,close fire sprinklers,turn on"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:48"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then close fire sprinklers

Scenario: 2
Given turn on
And output to console
When close fire sprinklers
Then turn on
