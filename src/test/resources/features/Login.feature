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
    When user enter "<USERNAME>" and "<PASSWORD>"
#    And user click on login button
    Examples:
      | sheetName | testCaseId | USERNAME      | PASSWORD     |
      | Sheet1    | Scenario1  | standard_user | secret_sauce |