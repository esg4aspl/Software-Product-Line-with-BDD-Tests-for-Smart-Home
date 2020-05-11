Feature: manualIlluminationAndAutomatedPerimeter1
Background: Setup
Given The environment is set up with "Turn on,UI output,Automated perimeter light on,UI output,Automated perimeter light off,UI output,Manual light on,UI output,Manual light off,UI output,Turn off"

Scenario: 0
When [
Then Turn on

Scenario: 1
Given Turn on
When UI output
Then Automated perimeter light on

Scenario: 2
Given Turn on
And UI output
When Automated perimeter light on
Then UI output

Scenario: 3
Given Turn on
And UI output
And Automated perimeter light on
When UI output
Then Automated perimeter light off

Scenario: 4
Given Turn on
And UI output
And Automated perimeter light on
And UI output
When Automated perimeter light off
Then UI output

Scenario: 5
Given Turn on
And UI output
And Automated perimeter light on
And UI output
And Automated perimeter light off
When UI output
Then Manual light on

Scenario: 6
Given Turn on
And UI output
And Automated perimeter light on
And UI output
And Automated perimeter light off
And UI output
When Manual light on
Then UI output

Scenario: 7
Given Turn on
And UI output
And Automated perimeter light on
And UI output
And Automated perimeter light off
And UI output
And Manual light on
When UI output
Then Manual light off

Scenario: 8
Given Turn on
And UI output
And Automated perimeter light on
And UI output
And Automated perimeter light off
And UI output
And Manual light on
And UI output
When Manual light off
Then UI output

Scenario: 9
Given Turn on
And UI output
And Automated perimeter light on
And UI output
And Automated perimeter light off
And UI output
And Manual light on
And UI output
And Manual light off
When UI output
Then Turn off

