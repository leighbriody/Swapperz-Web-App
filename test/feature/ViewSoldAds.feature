@ViewSoldAdsTesting

Feature: View Sold Ads

  Scenario: A User Wants To View Their Sold Ads
    Given the user is logged on their profile
    When the user clicks view sold ads
    Then the user will be able to see their sold ads
