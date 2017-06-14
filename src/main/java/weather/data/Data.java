package weather.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Andrzej on 11.06.2017.
 */
public abstract class Data
{
    protected  boolean empty;

    public abstract void refreshData()throws IOException;

    public String getStringData(String serwer) throws IOException
    {
        URL url = new URL(serwer);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200)
        {
            throw new IOException();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output = "";
        String line;

        while ((line = br.readLine()) != null)
        {
            output += line;
        }

        conn.disconnect();

        return output;
    }

    public Data()
    {
        this.empty = true;
    }

    public boolean empty()
    {
        return empty;
    }

    public void empty(boolean empty)
    {
        this.empty = empty;
    }
}
