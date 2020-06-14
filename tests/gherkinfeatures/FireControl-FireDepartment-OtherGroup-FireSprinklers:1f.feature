Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:1
Background: Setup
Given The environment is set up with "turn on,turn on"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:1"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then turn on

