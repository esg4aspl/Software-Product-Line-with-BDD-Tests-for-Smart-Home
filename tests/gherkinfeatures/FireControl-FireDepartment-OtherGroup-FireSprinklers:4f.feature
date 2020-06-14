Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:4
Background: Setup
Given The environment is set up with "turn on,call fire department"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:4"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then call fire department

