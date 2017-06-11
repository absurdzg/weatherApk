package weather.network;/**
 * Created by Andrzej on 11.06.2017.
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;


public class App extends Application
{

    private static final String FXML_MAIN_FORM_TEMPLATE = "/fxml/weather-main.fxml";

    public static void main(String[] args)
    {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_MAIN_FORM_TEMPLATE));
        primaryStage.setTitle("Hello");
        primaryStage.setScene(new Scene(root,800,500));
        primaryStage.show();
    }
}
