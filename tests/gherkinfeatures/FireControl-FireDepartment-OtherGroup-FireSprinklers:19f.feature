Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:19
Background: Setup
Given The environment is set up with "turn on,output to console,output to console"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:19"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then output to console

