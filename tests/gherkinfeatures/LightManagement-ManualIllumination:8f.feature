Feature: LightManagement-ManualIllumination:8
Background: Setup
Given The environment is set up with "turn on,output to console,turn off,turn on light manual"
When Feature name is "LightManagement-ManualIllumination:8"

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
Then turn on light manual

