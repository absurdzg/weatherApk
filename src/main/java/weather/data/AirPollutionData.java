package weather.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

/**
 * Created by Andrzej on 10.06.2017.
 */
public class AirPollutionData extends Data
{
    private static final String URL_AIR_POLUTION = "http://powietrze.gios.gov.pl/pjp/current/getAQIDetailsList?param=AQI";
    private static final int STATION_ID=544;

    private float PM10;
    private float PM2_5;


    public void  refreshData() throws IOException
    {
            String airPoluteString = super.getStringData(URL_AIR_POLUTION);

            JsonArray entries = new JsonParser().parse(airPoluteString).getAsJsonArray();


            for(int i=0;i<entries.size();i++)
            {
                JsonObject jsonObjectStation = (JsonObject) entries.get(i);
                if(jsonObjectStation.get("stationId").getAsInt()==STATION_ID)
                {
                    JsonObject jsonObjectValues = (JsonObject) jsonObjectStation.get("values");
                    PM10 = jsonObjectValues.get("PM10").getAsFloat();
                    PM2_5 = jsonObjectValues.get("PM2.5").getAsFloat();
                    empty=false;
                    return;
                }
            }

            System.out.println("Do not find id");
            throw new IOException();

    }

    public String showData()
    {
        if(empty)
        {
            return "-";
        }
        else
        {
            return "PM2.5:"+PM2_5+" PM10:"+PM10;
        }
    }

    public AirPollutionData()
    {
        super();
    }
}
