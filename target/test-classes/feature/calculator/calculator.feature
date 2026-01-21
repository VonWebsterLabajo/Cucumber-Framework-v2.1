@UICalculatorElementsVisibility
Feature: Check UI Elements visibility

	@CalculatorElementsVisibility
  Scenario: Calculator elements should be visible
    Given User is in the calculator page
    Then User should see the calculator elements
    
  @CalculatorDropdown
  Scenario: Calculator dropdown option are selectable
    Given User is in the calculator page
    Then User should see the calculator dropdown