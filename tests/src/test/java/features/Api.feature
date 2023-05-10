@ApiFeature
Feature: API

  @GetTest @Test1
  Scenario: GET method test
    Given API page is open with token
    When I use GET method
    Then Response of user role is Administrator
    And Status code for GET is 200

  @PostTest @Test2
  Scenario: POST method test
    Given API page is open with token1
    When I use POST method with random index
    Then Status code for POST is 201


