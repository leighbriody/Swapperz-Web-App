@EditProfileTesting

Feature: Edit Profile
  
  #When a user edits their profile with correct input
  Scenario: User Edits Profile With Correct Input
    Given a user has logged in
    And the user is on their profile
    When the user clicks edit profile
    And the user supplies correct information
    Then the user has successfully updated their profile


  #When a user enters incorrect data
  Scenario: User enters incorrect data (moblie no.)
    Given a user has logged in
    And the user is on their profile
    When the user clicks edit profile
    And the user supplies an invalid mobile
    Then the user has unsuccessfully updated their profile
