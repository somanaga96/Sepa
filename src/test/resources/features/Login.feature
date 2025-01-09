Feature: Login Feature

  @Login
  Scenario Outline: Login to swag labs
    Given user on Login page
    When user enter "<USERNAME>" and "<PASSWORD>"
    And user click on login button
    Examples:
      | USERNAME      | PASSWORD     |  |
      | standard_user | secret_sauce |  |