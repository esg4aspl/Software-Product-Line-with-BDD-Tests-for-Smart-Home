Feature: internet1
Background: Setup
Given The environment is set up with "Turn on,UI output,Internet input,Internet output,UI output,Turn off"

Scenario: 0
When [
Then Turn on

Scenario: 1
Given Turn on
When UI output
Then Internet input

Scenario: 2
Given Turn on
And UI output
When Internet input
Then Internet output

Scenario: 3
Given Turn on
And UI output
And Internet input
When Internet output
Then UI output

Scenario: 4
Given Turn on
And UI output
And Internet input
And Internet output
When UI output
Then Turn off

