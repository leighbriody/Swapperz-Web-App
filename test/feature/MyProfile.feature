@MyProfileTesting

Feature: My Profile

  #When a user clicks their username it will take them to their profile
  Scenario: User Clicks Their Username
    Given the user is logged in
    And the user clicks their username
    Then the user should be brought to their profile

