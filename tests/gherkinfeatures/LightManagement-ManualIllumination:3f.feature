Feature: LightManagement-ManualIllumination:3
Background: Setup
Given The environment is set up with "turn on,turn on light manual"
When Feature name is "LightManagement-ManualIllumination:3"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then turn on light manual

