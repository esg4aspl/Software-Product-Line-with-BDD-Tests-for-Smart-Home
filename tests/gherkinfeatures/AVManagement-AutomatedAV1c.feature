Feature: AVManagement-AutomatedAV1
Background: Setup
Given The environment is set up with "turn on,output to console,turn on AV automated,output to console,turn off AV automated,output to console,start AV automated,output to console,stop AV automated,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn on AV automated

Scenario: 2
Given turn on
And output to console
When turn on AV automated
Then output to console

Scenario: 3
Given turn on
And output to console
And turn on AV automated
When output to console
Then turn off AV automated

Scenario: 4
Given turn on
And output to console
And turn on AV automated
And output to console
When turn off AV automated
Then output to console

Scenario: 5
Given turn on
And output to console
And turn on AV automated
And output to console
And turn off AV automated
When output to console
Then start AV automated

Scenario: 6
Given turn on
And output to console
And turn on AV automated
And output to console
And turn off AV automated
And output to console
When start AV automated
Then output to console

Scenario: 7
Given turn on
And output to console
And turn on AV automated
And output to console
And turn off AV automated
And output to console
And start AV automated
When output to console
Then stop AV automated

Scenario: 8
Given turn on
And output to console
And turn on AV automated
And output to console
And turn off AV automated
And output to console
And start AV automated
And output to console
When stop AV automated
Then output to console

Scenario: 9
Given turn on
And output to console
And turn on AV automated
And output to console
And turn off AV automated
And output to console
And start AV automated
And output to console
And stop AV automated
When output to console
Then turn off

