Feature: FireControl-FireDepartment-OtherGroup1
Background: Setup
Given The environment is set up with "turn on,output to console,call other group for fire,output to console,call fire department,turn on siren,five minutes passed,turn off siren,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then call other group for fire

Scenario: 2
Given turn on
And output to console
When call other group for fire
Then output to console

Scenario: 3
Given turn on
And output to console
And call other group for fire
When output to console
Then call fire department

Scenario: 4
Given turn on
And output to console
And call other group for fire
And output to console
When call fire department
Then turn on siren

Scenario: 5
Given turn on
And output to console
And call other group for fire
And output to console
And call fire department
When turn on siren
Then five minutes passed

Scenario: 6
Given turn on
And output to console
And call other group for fire
And output to console
And call fire department
And turn on siren
When five minutes passed
Then turn off siren

Scenario: 7
Given turn on
And output to console
And call other group for fire
And output to console
And call fire department
And turn on siren
And five minutes passed
When turn off siren
Then output to console

Scenario: 8
Given turn on
And output to console
And call other group for fire
And output to console
And call fire department
And turn on siren
And five minutes passed
And turn off siren
When output to console
Then turn off

