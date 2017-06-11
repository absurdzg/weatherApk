package weather.data;


public class WindData
{
    private float speed;
    private int deg;

    public WindData(float speed, int deg)
    {
        this.speed = speed;
        this.deg = deg;
    }

    @Override
    public String toString()
    {
        final String DEGREE  = "\u00b0";
        return speed + "m/s " + deg +DEGREE;
    }
}
