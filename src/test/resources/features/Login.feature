Feature: Login Feature

  @Logi
  Scenario Outline: Login to swag labs
    Given user on Login page
    When user enter "<USERNAME>" and "<PASSWORD>"
    And user click on login button
    Examples:
      | USERNAME      | PASSWORD     |
      | standard_user | secret_sauce |

  @Login
  Scenario Outline: Login to swag labs
    Given user create screenshot folder from "<sheetName>" and "<testCaseId>"
    Given user on Login page
    When user enter user name and password from the test data sheet
#    And user click on login button
    Examples:
      | sheetName | testCaseId |
      | Sheet1    | Scenario1  |