Feature: LightManagement-ManualIllumination:2
Background: Setup
Given The environment is set up with "turn on,turn off"
When Feature name is "LightManagement-ManualIllumination:2"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then turn off
