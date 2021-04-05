@LoginTesting
Feature: Mens Category
As a user i want to be able to select 
MEN nav item to view mens categorys

#Scenario : When the user logs in with correct username and password
  Scenario: MEN nav item clicked

    Given the user is on the index.jsp page
    When the MEN nav item is clicked
    Then the user is brought to the pickMensCategory.jsp page
