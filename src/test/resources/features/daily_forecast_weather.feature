@daily-forecast-weather
Feature: Daily forecast weather

  @daily-forecast-weather-with-postal-code
  Scenario Outline: Daily forecast weather for give postal code

    Given I am provided with <postal_code>
    When I look up daily forecast weather
    Then I receive data for daily forecast weather
    And I verify weather and time

    Examples:
      |postal_code |
      |28546       |
      |36539       |
