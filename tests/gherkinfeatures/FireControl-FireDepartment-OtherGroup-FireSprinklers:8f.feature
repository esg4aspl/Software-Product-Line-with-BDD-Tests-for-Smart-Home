Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:8
Background: Setup
Given The environment is set up with "turn on,fire detected via smoke detector"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:8"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then fire detected via smoke detector

