Feature: manualWindows1
Background: Setup
Given The environment is set up with "Turn on,UI output,Manual window open,UI output,Manual window close,UI output,Turn off"

Scenario: 0
When [
Then Turn on

Scenario: 1
Given Turn on
When UI output
Then Manual window open

Scenario: 2
Given Turn on
And UI output
When Manual window open
Then UI output

Scenario: 3
Given Turn on
And UI output
And Manual window open
When UI output
Then Manual window close

Scenario: 4
Given Turn on
And UI output
And Manual window open
And UI output
When Manual window close
Then UI output

Scenario: 5
Given Turn on
And UI output
And Manual window open
And UI output
And Manual window close
When UI output
Then Turn off

