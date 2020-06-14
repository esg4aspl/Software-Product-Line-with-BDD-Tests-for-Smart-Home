Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:32
Background: Setup
Given The environment is set up with "turn on,output to console,fire detected via smoke detector,call fire department,turn on"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:32"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then fire detected via smoke detector

Scenario: 2
Given turn on
And output to console
When fire detected via smoke detector
Then call fire department

Scenario: 3
Given turn on
And output to console
And fire detected via smoke detector
When call fire department
Then turn on

