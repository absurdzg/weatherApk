package weather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import org.kordamp.ikonli.javafx.FontIcon;
import weather.data.*;
import weather.network.Timer;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashSet;


/**
 * Created by Andrzej on 11.06.2017.
 */
public class WeatherAppController
{
    AirPollutionData airPollutionData;
    WeatherData openWeatherSourceData;
    WeatherData meteoWawSourceData;
    WeatherData currentSource;
    Timer timer;

    @FXML
    private FontIcon icon;

    @FXML
    private Button refreshButton;

    @FXML
    private Button changeSourceButton;

    @FXML
    private RadioButton openWeatherSource;

    @FXML
    private RadioButton meteoWawSource;

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
        System.out.println("Init");
        refreshButtonClicked();
    }

    public void refreshButtonClicked()
    {
        timer.startTimer();

        refreshAllData();
        updateTime();
        bringUpDateShowing();
    }

    public void changeClicked()
    {
        if (openWeatherSource.isSelected())
        {
            currentSource = openWeatherSourceData;
            bringUpDateShowing();
        }
        else
        {
            currentSource = meteoWawSourceData;
            bringUpDateShowing();
        }
    }

    private void refreshAllData()
    {
        HashSet<Data> toRefreshData = new HashSet<>();
        toRefreshData.add(airPollutionData);
        toRefreshData.add(openWeatherSourceData);
        toRefreshData.add(meteoWawSourceData);
        errorIcon.setVisible(false);

        for (Data d : toRefreshData)
        {
            try
            {
                d.empty(false);
                d.refreshData();
            } catch (IOException e)
            {
                errorIcon.setVisible(true);
                d.empty(true);
            }
        }
    }

    private void bringUpDateShowing()
    {
        airPolutionLabelPM25.setText(airPollutionData.PM2_5());
        airPolutionLabelPM10.setText(airPollutionData.PM10());

        pressureLabel.setText(currentSource.pressure());
        temperatureLabel.setText(currentSource.temperature());
        humidityLabel.setText(currentSource.humidity());
        cloudsLabel.setText(currentSource.cloud());

        bringUpDateWindShowing();

    }

    private void bringUpDateWindShowing()
    {

        WindData windData = currentSource.windData();

        if (windData.empty())
        {
            windLabel.setText("N/A");
            iconWind.setIconLiteral("wi-na"); //zmiana ikonki
            iconWind.setRotate(0);
        }
        else
        {
            windLabel.setText(Float.toString(windData.getSpeed()));
            iconWind.setIconLiteral("wi-wind-direction");
            iconWind.setRotate(windData.getDeg());
        }
    }

    private void updateTime()
    {
        LocalTime today =LocalTime.now();

        String time = today.getHour() + ":";

        if (today.getMinute() < 10)
        {
            time += "0";
        }

        time += today.getMinute() + ":";

        if (today.getSecond() < 10)
        {
            time += "0";
        }
        time += today.getSecond();

        updatesTimeAndSource.setText(time);
    }
}
