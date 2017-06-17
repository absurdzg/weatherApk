package weather.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class AirPollutionData extends Data
{
    private static final String URL_AIR_POLLUTION = "http://powietrze.gios.gov.pl/pjp/current/getAQIDetailsList?param=AQI";
    private static final int STATION_ID=544; //numer Id stacji w formacie Json
    private static final int NUMBER_OF_DIGITS_AFTER_COMA=2;
    private static final String UNIT=" µ/m3";

    private float PM10;
    private float PM2_5;

    public AirPollutionData() //konstruktor bezargumentowy
    {
        super();
    }

    public void  refreshData() throws IOException
    {
            System.out.println("Start refresh air pollution data.");

            String airPoluteString = super.getStringData(URL_AIR_POLLUTION);

            JsonArray entries = new JsonParser().parse(airPoluteString).getAsJsonArray();


            for(int i=0;i<entries.size();i++)
            {
                JsonObject jsonObjectStation = (JsonObject) entries.get(i);

                if(jsonObjectStation.get("stationId").getAsInt()==STATION_ID)
                {
                    JsonObject jsonObjectValues = (JsonObject) jsonObjectStation.get("values");
                    PM10 = jsonObjectValues.get("PM10").getAsFloat();
                    PM2_5 = jsonObjectValues.get("PM2.5").getAsFloat();
                    super.empty(false);
                    return;
                }
            }

            System.out.println("Not recognise id.");
            throw new IOException();
    }

    public String PM10()
    {
        if (super.empty())
        {
            return N_A_INSCRIPTION;
        }
        return Float.toString(round(PM10))+UNIT ;
    }

    public String PM2_5()
    {
        if (super.empty())
        {
            return N_A_INSCRIPTION;
        }
        return Float.toString(round(PM2_5)) + UNIT;
    }


    private static float round(float value) //
    {
        if (NUMBER_OF_DIGITS_AFTER_COMA < 0)
        {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, NUMBER_OF_DIGITS_AFTER_COMA);
        value = value * factor;
        long tmp = Math.round(value);
        return (float) tmp / factor;
    }

}
