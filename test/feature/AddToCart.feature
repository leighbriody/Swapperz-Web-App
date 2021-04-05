@AddToCartTesting

Feature: A user wants to add an item to their cart

  #When a user adds an item to their cart
  Scenario: User Adds Item To Cart
    Given a user is logged in to the application
    When the user is on the index.jsp page
    And the user clicks the mens nav item
    And the user selects a category
    And the user adds the item to their cart
    Then the user has added an item to their cart
    
