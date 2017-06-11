package weather.data;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class WeatherDataMeteo extends WeatherData
{
    private static final String HUMIDITY_ID = "PARAM_0_RH";
    private static final String TEMPERATURE_ID = "PARAM_0_TA";
    private static final String PREESURE_ID = "PARAM_PR";
    private static final String WIND_SPEED_ID = "PARAM_WV";
    private static final String WIND_DEG_ID = "PARAM_0_WDABBR";

    private static final int N_DEG=0;
    private static final int NW_DEG=45;
    private static final int W_DEG=90;
    private static final int SW_DEG=135;
    private static final int S_DEG=180;
    private static final int SE_DEG=225;
    private static final int E_DEG=270;
    private static final int NE_DEG=315;

    private static final String URL_METEO = "http://www.meteo.waw.pl/";

    @Override
    public void refreshData() throws IOException
    {
        Document doc = Jsoup.connect(URL_METEO).get();

        temperature=floatFromId(TEMPERATURE_ID,doc);
        humidity=floatFromId(HUMIDITY_ID,doc);
        pressure=floatFromId(PREESURE_ID,doc);

        float windSpeed=floatFromId(WIND_SPEED_ID,doc);

        Element content = doc.getElementById(WIND_DEG_ID);
        int windDeg = windDirectionToDeg(content.text());

        windData=new WindData(windSpeed,windDeg);

    }

    private int windDirectionToDeg(String direction)
    {
        switch (direction)
        {
            case "N":
                return N_DEG;

            case "NW":
                return NW_DEG;

            case "W":
                return W_DEG;

            case "SW":
                return SW_DEG;

            case "S":
                return S_DEG;

            case "SE":
                return SE_DEG;

            case "E":
                return E_DEG;

            case"NE":
                return NE_DEG;

            default:
                return -1;

        }
    }

    private float floatFromId(String id,Document doc)
    {
        Element content = doc.getElementById(id);
        String properString=content.text().replace(',','.');
        return Float.parseFloat(properString);
    }

    public WeatherDataMeteo()
    {
        super();
    }

    @Override
    public String showCloudsData()
    {
        return "-";
    }
}
