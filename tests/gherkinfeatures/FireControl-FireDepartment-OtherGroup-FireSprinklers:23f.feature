Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:23
Background: Setup
Given The environment is set up with "turn on,output to console,turn on siren"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:23"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn on siren
