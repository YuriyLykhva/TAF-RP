Feature: Login to the Report Portal

  @Login
  Scenario Outline: Login with different users
    Given I have a User
    When user enters Login as <Login>
    And Password as <Password>
    Then user is logged in as <userName>
    Examples:
      | Login        | Password   | userName     |
      | "default"    | "1q2w3e"   | "DEFAULT"    |
      | "superadmin" | "erebus"   | "SUPERADMIN" |
