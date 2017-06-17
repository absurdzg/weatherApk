package weather.multithreading;

import weather.data.Data;

import java.io.IOException;

public class DownloadData implements Runnable
{
    private final Data data; //dane które będą pobierane

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
