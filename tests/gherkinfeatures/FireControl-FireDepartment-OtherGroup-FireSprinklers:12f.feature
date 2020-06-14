Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:12
Background: Setup
Given The environment is set up with "turn on,output to console,turn off,call other group for fire"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:12"

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
Then call other group for fire

