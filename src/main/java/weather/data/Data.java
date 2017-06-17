package weather.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public abstract class Data
{
    private boolean empty;

    static final String N_A_INSCRIPTION="N/A";

    public abstract void refreshData()throws IOException;

    String getStringData(String serwer) throws IOException
    {
        URL url = new URL(serwer);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200)
        {
            throw new IOException();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        StringBuilder output = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null)
        {
            output.append(line);
        }

        conn.disconnect();

        return output.toString();
    }

    Data()
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
