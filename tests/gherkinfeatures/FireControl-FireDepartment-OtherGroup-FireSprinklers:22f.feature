Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:22
Background: Setup
Given The environment is set up with "turn on,output to console,open fire sprinklers"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:22"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then open fire sprinklers

