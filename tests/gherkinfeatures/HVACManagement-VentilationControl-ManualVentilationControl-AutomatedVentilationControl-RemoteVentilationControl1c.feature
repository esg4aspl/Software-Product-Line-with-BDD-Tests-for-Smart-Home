Feature: HVACManagement-VentilationControl-ManualVentilationControl-AutomatedVentilationControl-RemoteVentilationControl1
Background: Setup
Given The environment is set up with "turn on,output to console,turn on ventilation automatic,output to console,turn off ventilation automatic,output to console,turn off ventilation manual,output to console,turn on ventilation manual,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then turn on ventilation automatic

Scenario: 2
Given turn on
And output to console
When turn on ventilation automatic
Then output to console

Scenario: 3
Given turn on
And output to console
And turn on ventilation automatic
When output to console
Then turn off ventilation automatic

Scenario: 4
Given turn on
And output to console
And turn on ventilation automatic
And output to console
When turn off ventilation automatic
Then output to console

Scenario: 5
Given turn on
And output to console
And turn on ventilation automatic
And output to console
And turn off ventilation automatic
When output to console
Then turn off ventilation manual

Scenario: 6
Given turn on
And output to console
And turn on ventilation automatic
And output to console
And turn off ventilation automatic
And output to console
When turn off ventilation manual
Then output to console

Scenario: 7
Given turn on
And output to console
And turn on ventilation automatic
And output to console
And turn off ventilation automatic
And output to console
And turn off ventilation manual
When output to console
Then turn on ventilation manual

Scenario: 8
Given turn on
And output to console
And turn on ventilation automatic
And output to console
And turn off ventilation automatic
And output to console
And turn off ventilation manual
And output to console
When turn on ventilation manual
Then output to console

Scenario: 9
Given turn on
And output to console
And turn on ventilation automatic
And output to console
And turn off ventilation automatic
And output to console
And turn off ventilation manual
And output to console
And turn on ventilation manual
When output to console
Then turn off

