package weather.data;


import java.io.IOException;

public class WindData extends Data
{
    private float speed;
    private int deg;

    public float getSpeed()
    {
        return speed;
    }

    public int getDeg()
    {
        return deg;
    }

    @Override
    public void refreshData() throws IOException
    {
    }

    public void setWindData(float speed, int deg)
    {
        this.speed = speed;
        this.deg = deg;
    }

    public WindData()
    {
        super();
    }
}
