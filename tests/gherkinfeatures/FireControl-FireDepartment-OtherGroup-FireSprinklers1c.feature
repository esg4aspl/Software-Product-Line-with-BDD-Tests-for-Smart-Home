Feature: FireControl-FireDepartment-OtherGroup-FireSprinklers1
Background: Setup
Given The environment is set up with "turn on,output to console,open fire sprinklers,output to console,close fire sprinklers,output to console,call other group for fire,output to console,call fire department,turn on siren,five minutes passed,turn off siren,output to console,turn off"

Scenario: 0
When [
Then turn on

Scenario: 1
Given turn on
When output to console
Then open fire sprinklers

Scenario: 2
Given turn on
And output to console
When open fire sprinklers
Then output to console

Scenario: 3
Given turn on
And output to console
And open fire sprinklers
When output to console
Then close fire sprinklers

Scenario: 4
Given turn on
And output to console
And open fire sprinklers
And output to console
When close fire sprinklers
Then output to console

Scenario: 5
Given turn on
And output to console
And open fire sprinklers
And output to console
And close fire sprinklers
When output to console
Then call other group for fire

Scenario: 6
Given turn on
And output to console
And open fire sprinklers
And output to console
And close fire sprinklers
And output to console
When call other group for fire
Then output to console

Scenario: 7
Given turn on
And output to console
And open fire sprinklers
And output to console
And close fire sprinklers
And output to console
And call other group for fire
When output to console
Then call fire department

Scenario: 8
Given turn on
And output to console
And open fire sprinklers
And output to console
And close fire sprinklers
And output to console
And call other group for fire
And output to console
When call fire department
Then turn on siren

Scenario: 9
Given turn on
And output to console
And open fire sprinklers
And output to console
And close fire sprinklers
And output to console
And call other group for fire
And output to console
And call fire department
When turn on siren
Then five minutes passed

Scenario: 10
Given turn on
And output to console
And open fire sprinklers
And output to console
And close fire sprinklers
And output to console
And call other group for fire
And output to console
And call fire department
And turn on siren
When five minutes passed
Then turn off siren

Scenario: 11
Given turn on
And output to console
And open fire sprinklers
And output to console
And close fire sprinklers
And output to console
And call other group for fire
And output to console
And call fire department
And turn on siren
And five minutes passed
When turn off siren
Then output to console

Scenario: 12
Given turn on
And output to console
And open fire sprinklers
And output to console
And close fire sprinklers
And output to console
And call other group for fire
And output to console
And call fire department
And turn on siren
And five minutes passed
And turn off siren
When output to console
Then turn off

