Feature: Core:6
Background: Setup
Given The environment is set up with "turn on,output to console,turn on"
When Feature name is "Core:6"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn on

