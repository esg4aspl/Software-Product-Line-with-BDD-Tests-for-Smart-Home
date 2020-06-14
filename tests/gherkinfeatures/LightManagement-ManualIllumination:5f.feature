Feature: LightManagement-ManualIllumination:5
Background: Setup
Given The environment is set up with "turn on,output to console,turn off,turn on"
When Feature name is "LightManagement-ManualIllumination:5"

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
Then turn on

