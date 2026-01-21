@UICalculatorOperationTest
Feature: Test Calculator Operation

  Scenario: Calculator add operation is working
    Given User is in the calculator page
    When User inputs number values
      | numberOne | numberTwo |
      |         5 |         6 |
    And User selects operation
      | operation |
      | Multiply  |
    And User clicks compute
    Then Verify if result matched
      | expectedResult |
      | Result: 30     |
