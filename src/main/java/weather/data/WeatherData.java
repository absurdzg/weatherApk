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
        windData = new WindData();
    }

    public String pressure()
    {
        if (super.empty())
        {
            return "N/A";
        }
        else
        {
            return Float.toString(pressure);
        }

    }

    public String temperature()
    {
        if (super.empty())
        {
            return "N/A";
        }
        else
        {
            return Float.toString(temperature);
        }
    }

    public WindData windData()
    {
        return windData;
    }

    public String humidity()
    {
        if (super.empty())
        {
            return "N/A";
        }
        else
        {
            return Float.toString(humidity);
        }
    }

    public abstract String cloud();

    @Override
    public void empty(boolean empty)
    {
        super.empty(empty);
        windData.empty(empty);
    }
}
