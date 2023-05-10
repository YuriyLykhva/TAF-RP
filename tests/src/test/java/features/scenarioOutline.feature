Feature: Final Bill Calculation

  @ScenarioOutline1
  Scenario Outline: Customer Bill Amount Calculation
    Given I hava a Customer
    When user enters initial bill amount as <InitialBillAmount>
    And Sales Tax Rate is <TaxRate> Percent
    Then final bill amount calculated is <CalculatedBillAmount>
    Examples:
    |InitialBillAmount|TaxRate|CalculatedBillAmount|
    |100              |10     |110                 |
    |200              |8      |216                 |
    |100              |1.55   |101.55              |
