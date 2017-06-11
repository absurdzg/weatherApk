package weather.data;

/**
 * Created by Andrzej on 11.06.2017.
 */
public abstract class WeatherData extends Data
{

    protected WindData windData;
    protected float temperature;
    protected float humidity;
    protected float pressure;

    public WeatherData()
    {
        super();
    }

    public String showPressureData()
    {
        return pressure+" Hpa";
    }

    public String showTemperatureData()
    {
        final String DEGREE  = "\u00b0";
        return temperature+DEGREE+"C";
    }

    public String showhumidityData()
    {
        return humidity+"%";
    }

    public String showWindData()
    {
        return windData.toString();
    }

    public abstract String showCloudsData();


}
