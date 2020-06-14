Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:20
Background: Setup
Given The environment is set up with "turn on,output to console,call other group for fire"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:20"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then call other group for fire

