Feature: LightManagement-ManualIllumination:10
Background: Setup
Given The environment is set up with "turn on,output to console,turn on"
When Feature name is "LightManagement-ManualIllumination:10"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn on

