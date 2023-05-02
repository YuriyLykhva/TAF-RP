Feature: Launches Page

  Scenario: Open Launches Page

    Given I have Signed in user
    When I click on Launches button
    Then Launches page opens
    And Browser is closed