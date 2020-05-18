Feature: manualWindowsAndAutomatedWindows1
Background: Setup
Given The environment is set up with "Turn on,UI output,Automated window open,UI output,Automated window  close,UI output,Manual window close,UI output,Manual window open,UI output,Turn off"

Scenario: 0
When [
Then Turn on

Scenario: 1
Given Turn on
When UI output
Then Automated window open

Scenario: 2
Given Turn on
And UI output
When Automated window open
Then UI output

Scenario: 3
Given Turn on
And UI output
And Automated window open
When UI output
Then Automated window  close

Scenario: 4
Given Turn on
And UI output
And Automated window open
And UI output
When Automated window  close
Then UI output

Scenario: 5
Given Turn on
And UI output
And Automated window open
And UI output
And Automated window  close
When UI output
Then Manual window close

Scenario: 6
Given Turn on
And UI output
And Automated window open
And UI output
And Automated window  close
And UI output
When Manual window close
Then UI output

Scenario: 7
Given Turn on
And UI output
And Automated window open
And UI output
And Automated window  close
And UI output
And Manual window close
When UI output
Then Manual window open

Scenario: 8
Given Turn on
And UI output
And Automated window open
And UI output
And Automated window  close
And UI output
And Manual window close
And UI output
When Manual window open
Then UI output

Scenario: 9
Given Turn on
And UI output
And Automated window open
And UI output
And Automated window  close
And UI output
And Manual window close
And UI output
And Manual window open
When UI output
Then Turn off

