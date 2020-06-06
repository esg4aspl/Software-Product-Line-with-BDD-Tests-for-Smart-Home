Feature: UI-TouchScreen-Internet-Privacy-RSA32
Background: Setup
Given The environment is set up with "turn on,output to console,input via Internet,send RSA encrypted input message,output via Internet,turn on"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then input via Internet

Scenario: 2
Given turn on
And output to console
When input via Internet
Then send RSA encrypted input message

Scenario: 3
Given turn on
And output to console
And input via Internet
When send RSA encrypted input message
Then output via Internet

Scenario: 4
Given turn on
And output to console
And input via Internet
And send RSA encrypted input message
When output via Internet
Then turn on

