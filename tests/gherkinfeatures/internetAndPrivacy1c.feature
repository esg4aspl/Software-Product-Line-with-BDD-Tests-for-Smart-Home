Feature: internetAndPrivacy1
Background: Setup
Given The environment is set up with "Turn on,UI output,Internet input,Send private input message,Internet output,Send private output message,UI output,Turn off"

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
Then Send private input message

Scenario: 3
Given Turn on
And UI output
And Internet input
When Send private input message
Then Internet output

Scenario: 4
Given Turn on
And UI output
And Internet input
And Send private input message
When Internet output
Then Send private output message

Scenario: 5
Given Turn on
And UI output
And Internet input
And Send private input message
And Internet output
When Send private output message
Then UI output

Scenario: 6
Given Turn on
And UI output
And Internet input
And Send private input message
And Internet output
And Send private output message
When UI output
Then Turn off

