package weatherbit;

import context.Context;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class WeatherBit {

    private Context context;

    public WeatherBit(Context context) {
        this.context = context;
        RestAssured.baseURI = context.getProperties().getProperty("url");
    }

    // Current weather related methods
    public RequestSpecification currentByLatLon(double lat, double lon) {
        // Prepare request
        return given().queryParam("lat", lat).queryParam("lon", lon).queryParam("key",
                context.getProperties().getProperty("key"));
    }

    public Response getCurrent(RequestSpecification request) {
        // Make get request
        return request.when().get("current");
    }

    public String getStateCodeFromCurrent(Response response) {
        // NOTE: Index 0 is used as combination of
        // lat and lon resolves to one unique location
        return response.jsonPath().getString("data[0].state_code").toString();
    }

    // Daily forecast weather related methods
    public RequestSpecification dailyForecastByPostalCode(int postalCode) {
        // Prepare request
        return given().queryParam("postal_code", postalCode).queryParam("key",
                context.getProperties().getProperty("key"));
    }

    public Response getDailyForecast(RequestSpecification request) {
        // Make get request
        return request.when().get("forecast/daily");
    }

    public List<DailyForecast> getDailyForecastDetails(Response response) {
        return response.jsonPath().getList("data", DailyForecast.class);
    }
}
