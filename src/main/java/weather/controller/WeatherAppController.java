package weather.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import weather.data.AirPollutionData;
import weather.data.WeatherData;
import weather.data.WeatherDataMeteo;
import weather.data.WeatherDataOpenWeather;

import java.io.IOException;
import java.time.LocalTime;


/**
 * Created by Andrzej on 11.06.2017.
 */
public class WeatherAppController
{
    AirPollutionData airPollutionData;
    WeatherData firstWeatherSourceData;
    WeatherData secondWeatherSourceData;


    @FXML
    private Button refreshButton;

    @FXML
    private Button changeSourceButton;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label windLabel;

    @FXML
    private Label airPolutionLabel;

    @FXML
    private Label cloudsLabel;

    @FXML
    private Label updatesTime;

    public WeatherAppController()
    {
        this.airPollutionData = new AirPollutionData();
        this.firstWeatherSourceData = new WeatherDataOpenWeather();
        this.secondWeatherSourceData = new WeatherDataMeteo();
    }

    public void refreshButtonClicked()
    {
        refreshAllData();
        bringUpDateShowing();
        updateTime();
    }

    public void buuu()
    {
        System.out.println("bajere");
    }

    private void refreshAllData()
    {
        try
        {
            airPollutionData.refreshData();
        }
        catch (IOException e)
        {
            airPollutionData =new AirPollutionData();
        }

        try
        {
            firstWeatherSourceData.refreshData();
        }
        catch (IOException e)
        {
            firstWeatherSourceData = new WeatherDataOpenWeather();
        }

        try
        {
            secondWeatherSourceData.refreshData();
        }
        catch (IOException e)
        {
            secondWeatherSourceData= new WeatherDataMeteo();
        }

    }

    private void bringUpDateShowing()
    {
        airPolutionLabel.setText(airPollutionData.showData());

        WeatherData curentWeaterData=secondWeatherSourceData;

        pressureLabel.setText(curentWeaterData.showPressureData());
        temperatureLabel.setText(curentWeaterData.showTemperatureData());
        humidityLabel.setText(curentWeaterData.showhumidityData());
        windLabel.setText(curentWeaterData.showWindData());
        cloudsLabel.setText(curentWeaterData.showCloudsData());

    }

    private void updateTime()
    {
        LocalTime today =LocalTime.now();
        String time=today.getHour() + ":" + today.getMinute()+ ":" +today.getSecond();
        updatesTime.setText(time);
    }
}
