Feature: LightManagement-ManualIllumination:17
Background: Setup
Given The environment is set up with "turn on,output to console,turn off light manual,turn off"
When Feature name is "LightManagement-ManualIllumination:17"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn off light manual

Scenario: 2
Given turn on
And output to console
When turn off light manual
Then turn off

