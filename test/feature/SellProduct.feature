@SellProductTesting

Feature: Sell Product

  #When a user sells a product with correct inputs
  Scenario: User Sells Product With Correct Input
    Given a user is logged in
    When the user is on the index.jsp page
    And the user clicks the sell tab
    And the user fills out their advertisement details
    And the user clicks the sell product input
    Then they should have a listed advertisement



  #When a user enters an integer instead of a string
  Scenario: User Tries To Place Advertisement With Incorrect String Input
    Given a user is logged in
    When the user is on the index.jsp page
    And the user clicks the sell tab
    And the user fills out their advertisement details with an incorrect integer
    And the user clicks the sell product input
    Then they should be an error message