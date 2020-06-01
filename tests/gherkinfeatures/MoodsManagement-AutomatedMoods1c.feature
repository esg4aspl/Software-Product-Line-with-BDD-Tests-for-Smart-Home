Feature: MoodsManagement-AutomatedMoods1
Background: Setup
Given The environment is set up with "turn on,output to console,turn on moods automatic,output to console,turn off moods automatic,output to console,dim moods automatic,output to console,brighten moods automatic,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn on moods automatic

Scenario: 2
Given turn on
And output to console
When turn on moods automatic
Then output to console

Scenario: 3
Given turn on
And output to console
And turn on moods automatic
When output to console
Then turn off moods automatic

Scenario: 4
Given turn on
And output to console
And turn on moods automatic
And output to console
When turn off moods automatic
Then output to console

Scenario: 5
Given turn on
And output to console
And turn on moods automatic
And output to console
And turn off moods automatic
When output to console
Then dim moods automatic

Scenario: 6
Given turn on
And output to console
And turn on moods automatic
And output to console
And turn off moods automatic
And output to console
When dim moods automatic
Then output to console

Scenario: 7
Given turn on
And output to console
And turn on moods automatic
And output to console
And turn off moods automatic
And output to console
And dim moods automatic
When output to console
Then brighten moods automatic

Scenario: 8
Given turn on
And output to console
And turn on moods automatic
And output to console
And turn off moods automatic
And output to console
And dim moods automatic
And output to console
When brighten moods automatic
Then output to console

Scenario: 9
Given turn on
And output to console
And turn on moods automatic
And output to console
And turn off moods automatic
And output to console
And dim moods automatic
And output to console
And brighten moods automatic
When output to console
Then turn off

