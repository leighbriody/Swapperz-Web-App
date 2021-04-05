Feature: Pick Womens Category

  # The first example has two steps
  Scenario: User picks WOMEN nav item
    Given the user is on the index.jsp
    When the user clicks the WOMEN nav item
    Then the user should be brought to the pickWomensCategory.jsp page

 