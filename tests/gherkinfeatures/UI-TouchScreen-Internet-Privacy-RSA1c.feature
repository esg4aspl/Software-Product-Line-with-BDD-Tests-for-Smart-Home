Feature: UI-TouchScreen-Internet-Privacy-RSA1
Background: Setup
Given The environment is set up with "turn on,output to console,input via Internet,send RSA encrypted input message,output via Internet,send RSA encrypted output message,output to console,input via touchscreen,output via touchscreen,output to console,turn off"

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
Then send RSA encrypted output message

Scenario: 5
Given turn on
And output to console
And input via Internet
And send RSA encrypted input message
And output via Internet
When send RSA encrypted output message
Then output to console

Scenario: 6
Given turn on
And output to console
And input via Internet
And send RSA encrypted input message
And output via Internet
And send RSA encrypted output message
When output to console
Then input via touchscreen

Scenario: 7
Given turn on
And output to console
And input via Internet
And send RSA encrypted input message
And output via Internet
And send RSA encrypted output message
And output to console
When input via touchscreen
Then output via touchscreen

Scenario: 8
Given turn on
And output to console
And input via Internet
And send RSA encrypted input message
And output via Internet
And send RSA encrypted output message
And output to console
And input via touchscreen
When output via touchscreen
Then output to console

Scenario: 9
Given turn on
And output to console
And input via Internet
And send RSA encrypted input message
And output via Internet
And send RSA encrypted output message
And output to console
And input via touchscreen
And output via touchscreen
When output to console
Then turn off

