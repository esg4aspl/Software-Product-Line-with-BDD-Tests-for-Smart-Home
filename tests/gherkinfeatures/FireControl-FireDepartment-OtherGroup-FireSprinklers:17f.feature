Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:17
Background: Setup
Given The environment is set up with "turn on,output to console,turn off,fire detected via smoke detector"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:17"

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
Then fire detected via smoke detector

