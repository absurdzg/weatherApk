package weather.data;


import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class WindData extends Data
{
    private float speed;
    private int deg;

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

    public String windSpeed()
    {
        if(super.empty())
        {
            return N_A_INSCRIPTION;
        }
        else
        {
            return Float.toString(speed);
        }
    }

    public void windDeg(FontIcon windIcon)
    {
        if(super.empty())
        {
            windIcon.setIconLiteral("wi-na"); //zmiana ikonki na brak danych
            windIcon.setRotate(0); // zeruje obrot ikonki
        }
        else
        {
            windIcon.setIconLiteral("wi-wind-direction");
            windIcon.setRotate(deg); //obracam ikone o odpowiedni kat
        }
    }
}
