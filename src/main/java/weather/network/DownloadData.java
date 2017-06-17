package weather.network;

import weather.data.Data;

import java.io.IOException;

/**
 * Created by Andrzej on 16.06.2017.
 */
public class DownloadData implements Runnable
{
    Data data; //dane które będą pobierane

    public DownloadData(Data data)
    {
        this.data = data;
    }

    @Override
    public void run()
    {
        try
        {
            data.empty(false);
            data.refreshData();
        } catch (IOException e)
        {
            data.empty(true);
            System.out.println("Error conection.");
        }
    }
}
