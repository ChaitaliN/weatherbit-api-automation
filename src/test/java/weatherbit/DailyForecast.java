package weatherbit;

import java.util.Map;

public class DailyForecast {

    private String datetime;
    private Map<String, String> weather;

    public String getDatetime() {
        return datetime;
    }

    public Map<String, String> getWeatherDetails() {
        return weather;
    }

}
