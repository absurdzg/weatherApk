package weather.network;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import weather.controller.WeatherAppController;

public class Timer
{
    private WeatherAppController controller;
    private static final int REFRESH_TIME = 2 * 60 * 1000; //time in millis
    private Timeline timeline;


    public Timer(WeatherAppController controller)
    {
        timeline = new Timeline();
        this.controller = controller;
    }

    public void startTimer()
    {
        if (timeline.getStatus().equals(Animation.Status.RUNNING)) //nie nakladamy na siebie dwoch timerow
        {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(
                Duration.millis(REFRESH_TIME),
                ae -> controller.refreshButtonClicked()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
