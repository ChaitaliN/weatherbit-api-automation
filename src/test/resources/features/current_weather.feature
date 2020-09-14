@current-weather
Feature: Current weather

  @current-weather-with-lat-lon
  Scenario Outline: Current weather for given lat and lon

    Given I am provided with <lat> and <lon>
    When I look up the current weather
    Then I receive data for the current weather
    And I verify state code is "<state_code>"

    Examples:
      |lat       |lon        | state_code |
      |40.730610 |-73.935242 | NY         |
      |50.340660 |-80.935242 | 08         |
