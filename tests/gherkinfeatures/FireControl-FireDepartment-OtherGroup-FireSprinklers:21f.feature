Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:21
Background: Setup
Given The environment is set up with "turn on,output to console,call fire department"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:21"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then call fire department

