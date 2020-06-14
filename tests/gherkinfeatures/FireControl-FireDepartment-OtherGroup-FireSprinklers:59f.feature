Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:59
Background: Setup
Given The environment is set up with "turn on,output to console,fire detected via smoke detector,call fire department,turn on siren,call fire department"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:59"

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
Then turn on siren

Scenario: 4
Given turn on
And output to console
And fire detected via smoke detector
And call fire department
When turn on siren
Then call fire department

