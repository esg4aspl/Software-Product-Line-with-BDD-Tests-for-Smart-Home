Feature: LightManagement-ManualIllumination:12
Background: Setup
Given The environment is set up with "turn on,output to console,turn on light manual,turn on"
When Feature name is "LightManagement-ManualIllumination:12"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn on light manual

Scenario: 2
Given turn on
And output to console
When turn on light manual
Then turn on
