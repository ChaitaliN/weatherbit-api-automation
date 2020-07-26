package stepDefinition;

import context.ContextManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import weatherbit.WeatherBit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CurrentWeather {

    private RequestSpecification request;
    private Response response;
    private WeatherBit weather;

    public CurrentWeather(ContextManager context) {
        weather = new WeatherBit(context);
    }

    @Given("I am provided with {double} and {double}")
    public void i_am_provided_with_and(double lat, double lon) throws Throwable {
        // Prepare request with lat/lon
        request = weather.currentByLatLon(lat, lon);
    }

    @When("I look up the current weather")
    public void i_look_up_the_current_weather() throws Throwable {
        // Make get request
        response = weather.getCurrent(request);
    }

    @Then("I receive data for the current weather")
    public void i_receive_data_for_the_current_weather() throws Throwable {
        // Validate response for status code
        response.then().statusCode(200);
    }

    @Then("I verify state code is {string}")
    public void i_verify_state_code_is(String expectedStateCode) throws Throwable {
        // Verify state code
        String stateCode = weather.getStateCodeFromCurrent(response);
        assertThat(stateCode, equalTo(expectedStateCode));
    }

}
