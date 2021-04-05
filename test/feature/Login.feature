@LoginTesting
Feature: Login
  As a user
  I want to login
  In order to use the application

#Scenario : When the user logs in with correct username and password
  Scenario: correct credentials
    User logs in with correct credentials

    Given the user is on the login.jsp page
    When the username and password is correct
    And the login button is clicked
    Then the user is brought to the index.jsp

#Scenario : When the user logs in with incorrect username and correct password
  Scenario: incorrect username
    User logs in with incorrect username

    Given the user is on the login.jsp page
    When the username is incorrect  and password is correct
    And the login button is clicked
    Then the user should still be on the login.jsp

#Scenario : When the user logs in with correct username but incorrect password
  Scenario: incorrect password
    User logs in with incorrect password

    Given the user is on the login.jsp page
    When the username is correct  and password is incorrect
    And the login button is clicked
    Then the user should still be on the login.jsp