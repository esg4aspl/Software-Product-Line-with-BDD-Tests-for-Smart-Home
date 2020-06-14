Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:5
Background: Setup
Given The environment is set up with "turn on,open fire sprinklers"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:5"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then open fire sprinklers

