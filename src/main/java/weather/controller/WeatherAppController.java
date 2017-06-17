package weather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import org.kordamp.ikonli.javafx.FontIcon;
import weather.data.*;
import weather.multithreading.DownloadData;
import weather.multithreading.Timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class WeatherAppController
{

    private static final int COUNT_OF_SOURCE=3;

    private AirPollutionData airPollutionData;
    private WeatherData openWeatherSourceData;
    private WeatherData meteoWawSourceData;
    private WeatherData currentSource;
    private Timer timer;

    @FXML
    private RadioButton openWeatherSourceRadioButton;

    @FXML
    private FontIcon errorIcon;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label windLabel;

    @FXML
    private Label airPolutionLabelPM25;

    @FXML
    private Label airPolutionLabelPM10;

    @FXML
    private Label cloudsLabel;

    @FXML
    private Label updatesTimeAndSource;

    @FXML
    private FontIcon iconWind;

    public WeatherAppController()
    {
        this.airPollutionData = new AirPollutionData();
        this.openWeatherSourceData = new WeatherDataOpenWeather();
        this.meteoWawSourceData = new WeatherDataMeteo();
        this.currentSource = openWeatherSourceData;
        this.timer = new Timer(this);
    }

    public void initialize()
    {
        System.out.println("Initialize controller.");
        refreshButtonClicked();
    }

    public void refreshButtonClicked()
    {
        System.out.println("Refresh all data.");
        timer.startTimer(); //restartuje timer

        updateTime();
        refreshAllData();
        bringUpDataShowing();
    }

    public void changeClicked() //zmiana zrodła danych
    {
        if (openWeatherSourceRadioButton.isSelected())
        {
            currentSource = openWeatherSourceData;
            bringUpDataShowing();
        }
        else
        {
            currentSource = meteoWawSourceData;
            bringUpDataShowing();
        }
    }

    private void refreshAllData()
    {
        Data[] toRefreshData = new Data[COUNT_OF_SOURCE];

        toRefreshData[0]=airPollutionData;
        toRefreshData[1]=openWeatherSourceData;
        toRefreshData[2]=meteoWawSourceData;

        Runnable[] runners=new Runnable[COUNT_OF_SOURCE];

        for(int i=0; i<COUNT_OF_SOURCE; i++) //wprowadzam wielowatkowosc aby szybciej sciagac dane
        {
            runners[i]=new DownloadData(toRefreshData[i]);
        }

        ExecutorService es= Executors.newCachedThreadPool();

        for(Runnable runnable:runners)
        {
            es.execute(runnable);
        }

        es.shutdown();

        try
        {
            while (!es.awaitTermination(1, TimeUnit.MINUTES)); //czekam az wszystkie dane zostana sciagniete
            errorIcon.setVisible(false);
        }
        catch (InterruptedException e)
        {
            System.out.println("Too long time of download data.");
            errorIcon.setVisible(true);
        }

        for(Data data:toRefreshData)
        {
            if(data.empty())
                errorIcon.setVisible(true);
        }

    }

    private void bringUpDataShowing() //aktualizuje wyswietlane dane
    {
        airPolutionLabelPM25.setText(airPollutionData.PM2_5());
        airPolutionLabelPM10.setText(airPollutionData.PM10());

        pressureLabel.setText(currentSource.pressure());
        temperatureLabel.setText(currentSource.temperature());
        humidityLabel.setText(currentSource.humidity());
        cloudsLabel.setText(currentSource.cloud());

        windLabel.setText(currentSource.windData().windSpeed());
        currentSource.windData().windDeg(iconWind);
    }


    private void updateTime()
    {
        Date day=new Date();

        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");

        String time=dateFormat.format(day);

        System.out.println("Time update:"+time);
        updatesTimeAndSource.setText(time);
    }
}
