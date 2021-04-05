@AddBrandTesting
Feature: Admin adding a brand
As an admin
I can add a brand
So that users have more choices when browsing

#Scenario: When an admin adds a brand with correct input
Scenario: correct input
    Admin Logs in with correct details and adds a brand

    Given an admin is on the login.jsp page
    When the admin logs in
    And the admin is on the index.jsp page
    And the admin clicks the ADMIN
    And the admin clicks ADD BRAND button
    And the admin enters a correctly inputted brand
    And the admin clicks the add brand button
    Then the admin will be notified of a successful brand insert
