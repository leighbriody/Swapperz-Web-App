Feature: View Womens Hoodies

  # When a user registers with correct details
  Scenario: User Registers With Correct Credentials
    Given the user is on the register.jsp page
    When i fill in my details
    And i click the register button
    Then i should be on the login.jsp

 # When a user registers with an invalid phone number 
  Scenario: User Registers With Correct Credentials
    Given the user is on the register.jsp page
    When i fill in my details with incorrect phone number
    And i click the register button
    Then i should still be on the register.jsp page
    And there should be an error message