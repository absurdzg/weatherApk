package weather.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

/**
 * Created by Andrzej on 11.06.2017.
 */
public class WeatherDataOpenWeather extends WeatherData
{

    private int clouds;

    private static final String URL_OPEN_WEATHER = "http://api.openweathermap.org/data/2.5/weather?q=Warsaw&APPID=52ad7ea93477d9d80c6e2ac9749af2ef";
    private static final double TRIPLE_POINT= 273.16;

    @Override
    public void refreshData() throws IOException
    {
            String wheterStringData = super.getStringData(URL_OPEN_WEATHER);

            JsonObject weatherJsonData = new JsonParser().parse(wheterStringData).getAsJsonObject();


            JsonObject mainWeatherComponents= (JsonObject) weatherJsonData.get("main");

            temperature=(float)(mainWeatherComponents.get("temp").getAsDouble()-TRIPLE_POINT);

            humidity=mainWeatherComponents.get("humidity").getAsFloat();

            pressure=mainWeatherComponents.get("pressure").getAsFloat();



            JsonObject windWeather =   (JsonObject) weatherJsonData.get("wind");

            windData=new WindData(windWeather.get("speed").getAsFloat(),windWeather.get("deg").getAsInt());


            JsonObject cloudsWeather =   (JsonObject) weatherJsonData.get("clouds");

            clouds= cloudsWeather.get("all").getAsInt();

            empty=false;
    }

    public WeatherDataOpenWeather()
    {
        super();
    }

    @Override
    public String showCloudsData()
    {
        return clouds+"%";
    }
}
