Feature: Security-AuthenticationDevice-Keypad-IntrusionDetectionDevice-GlassbreakSensors4
Background: Setup
Given The environment is set up with "turn on,intrusion detected via glassbreak sensor"

Scenario: 0
When [
Then turn on

Scenario: 1
When turn on
Then intrusion detected via glassbreak sensor

