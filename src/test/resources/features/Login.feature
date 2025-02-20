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
    And user click on login button
    Then user update the value in Excel
    Examples:
      | sheetName | testCaseId |
      | Sheet2    | Scenario1  |
      | Sheet2    | Scenario2  |
#      | Sheet2    | Scenario3  |
#      | Sheet2    | Scenario4  |
#      | Sheet2    | Scenario5  |
#      | Sheet2    | Scenario6  |
#      | Sheet2    | Scenario7  |
#      | Sheet2    | Scenario8  |