Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:6
Background: Setup
Given The environment is set up with "turn on,close fire sprinklers"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:6"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then close fire sprinklers

