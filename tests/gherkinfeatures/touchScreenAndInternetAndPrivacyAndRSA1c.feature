Feature: touchScreenAndInternetAndPrivacyAndRSA1
Background: Setup
Given The environment is set up with "Turn on,UI output,Internet input,Encrypt input with RSA,Send private input message,Decrypt input with RSA,Internet output,Encrypt output with RSA,Send private output message,Decrypt output with RSA,UI output,Touchscreen input,Touchscreen output,UI output,Turn off"

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
Then Encrypt input with RSA

Scenario: 3
Given Turn on
And UI output
And Internet input
When Encrypt input with RSA
Then Send private input message

Scenario: 4
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
When Send private input message
Then Decrypt input with RSA

Scenario: 5
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
And Send private input message
When Decrypt input with RSA
Then Internet output

Scenario: 6
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
And Send private input message
And Decrypt input with RSA
When Internet output
Then Encrypt output with RSA

Scenario: 7
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
And Send private input message
And Decrypt input with RSA
And Internet output
When Encrypt output with RSA
Then Send private output message

Scenario: 8
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
And Send private input message
And Decrypt input with RSA
And Internet output
And Encrypt output with RSA
When Send private output message
Then Decrypt output with RSA

Scenario: 9
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
And Send private input message
And Decrypt input with RSA
And Internet output
And Encrypt output with RSA
And Send private output message
When Decrypt output with RSA
Then UI output

Scenario: 10
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
And Send private input message
And Decrypt input with RSA
And Internet output
And Encrypt output with RSA
And Send private output message
And Decrypt output with RSA
When UI output
Then Touchscreen input

Scenario: 11
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
And Send private input message
And Decrypt input with RSA
And Internet output
And Encrypt output with RSA
And Send private output message
And Decrypt output with RSA
And UI output
When Touchscreen input
Then Touchscreen output

Scenario: 12
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
And Send private input message
And Decrypt input with RSA
And Internet output
And Encrypt output with RSA
And Send private output message
And Decrypt output with RSA
And UI output
And Touchscreen input
When Touchscreen output
Then UI output

Scenario: 13
Given Turn on
And UI output
And Internet input
And Encrypt input with RSA
And Send private input message
And Decrypt input with RSA
And Internet output
And Encrypt output with RSA
And Send private output message
And Decrypt output with RSA
And UI output
And Touchscreen input
And Touchscreen output
When UI output
Then Turn off

