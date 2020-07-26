package stepDefinition;

import java.util.List;

import context.ContextManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import weatherbit.DailyForecast;
import weatherbit.WeatherBit;

import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DailyForecastWeather {

    private RequestSpecification request;
    private Response response;
    private WeatherBit weather;

    public DailyForecastWeather(ContextManager context) {
        weather = new WeatherBit(context);
    }

    @Given("I am provided with {int}")
    public void i_am_provided_with(Integer postalCode) throws Throwable {
        // Prepare request with postal code
        request = weather.dailyForecastByPostalCode(postalCode);
    }

    @When("I look up daily forecast weather")
    public void i_look_up_daily_forecast_weather() throws Throwable {
        // Make get request
        response = weather.getDailyForecast(request);
    }

    @Then("I receive data for daily forecast weather")
    public void i_receive_data_for_daily_forecast_weather() throws Throwable {
        // Validate response for status code
        response.then().statusCode(200);
    }

    @Then("I verify weather and time")
    public void i_verify_weather_and_time() throws Throwable {

        // Get daily forecast
        List<DailyForecast> forecastDailyList = weather.getDailyForecastDetails(response);

        // Verify if list is empty
        assertThat(forecastDailyList, not(IsEmptyCollection.empty()));

        // Verify if datetime is empty
        assertThat(forecastDailyList.get(0).getDatetime(), not(emptyOrNullString()));

        // Verify if weather details are empty
        assertThat(forecastDailyList.get(0).getWeatherDetails(), hasKey("description"));
    }
}
