package weather.data;


public abstract class WeatherData extends Data
{

    protected WindData windData;
    protected float temperature;
    protected float humidity;
    protected float pressure;

    WeatherData()
    {
        super();
        windData = new WindData();
    }

    public String pressure()
    {
        if (super.empty())
        {
            return N_A_INSCRIPTION;
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
            return N_A_INSCRIPTION;
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
            return N_A_INSCRIPTION;
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
