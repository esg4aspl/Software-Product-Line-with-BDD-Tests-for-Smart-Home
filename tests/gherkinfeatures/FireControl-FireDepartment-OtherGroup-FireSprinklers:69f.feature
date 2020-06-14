Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers:69
Background: Setup
Given The environment is set up with "turn on,output to console,fire detected via smoke detector,close fire sprinklers"
When Feature name is "FireControl-FireDepartment-OtherGroup-FireSprinklers:69"

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
Then close fire sprinklers

