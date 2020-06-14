Feature: LightManagement-ManualIllumination:11
Background: Setup
Given The environment is set up with "turn on,output to console,output to console"
When Feature name is "LightManagement-ManualIllumination:11"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then output to console

