@LoginTesting
Feature: Login
  As a user
  I want to login
  In order to use the application

#Scenario : When the user logs in with correct username and password
  Scenario: correct credentials
    User logs in with correct credentials

    Given a user is on the login.jsp page
    When the username and password is put in correctly 
    And the login button is pressed
    Then the user is brought to the index.jsp
    When the MEN  nav item is clicked
    Then the pickMensCategory.jsp should be displayed