Feature: Security-AuthenticationDevice-Keypad-IntrusionDetectionDevice-GlassbreakSensors34
Background: Setup
Given The environment is set up with "turn on,output to console,intrusion detected via glassbreak sensor,turn on bell,turn on"

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
Then turn on

