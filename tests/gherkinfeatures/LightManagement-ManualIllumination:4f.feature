Feature: LightManagement-ManualIllumination:4
Background: Setup
Given The environment is set up with "turn on,turn off light manual"
When Feature name is "LightManagement-ManualIllumination:4"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then turn off light manual

