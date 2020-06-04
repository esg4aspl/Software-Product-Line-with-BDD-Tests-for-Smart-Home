Feature: Security-AuthenticationDevice-Keypad-IntrusionDetectionDevice-GlassbreakSensors43
Background: Setup
Given The environment is set up with "turn on,output to console,intrusion detected via glassbreak sensor,turn on bell,five minutes passed,turn off bell,output to console"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then intrusion detected via glassbreak sensor

Scenario: 2
Given turn on
And output to console
When intrusion detected via glassbreak sensor
Then turn on bell

Scenario: 3
Given turn on
And output to console
And intrusion detected via glassbreak sensor
When turn on bell
Then five minutes passed

Scenario: 4
Given turn on
And output to console
And intrusion detected via glassbreak sensor
And turn on bell
When five minutes passed
Then turn off bell

Scenario: 5
Given turn on
And output to console
And intrusion detected via glassbreak sensor
And turn on bell
And five minutes passed
When turn off bell
Then output to console

