@login
Feature: Login Functionality

  Scenario: Login
    Given User navigate to webshop login page
      | strategyType | locatorTag | message |
      | text         | a          | Log in  |
    When User should input valid login credentials
      | username         | password |
      | fornis@email.com | james123 |
    And Verify successful login
    And User click logout button
    Then Verify successful logout
