package weather.network;/**
 * Created by Andrzej on 11.06.2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;



public class App extends Application
{

    private static final String[] LOGOS = {"/image/icon16.png", "/image/icon24.png", "/image/icon32.png"};

    private static final String FXML_MAIN_FORM_TEMPLATE = "/fxml/weather-main.fxml";

    public static void main(String[] args)
    {
       launch(args);
    }

    private void addLogo(Stage stage)
    {
        for (String logoPath : LOGOS)
        {
            stage.getIcons().add(new Image(logoPath));
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {

        Parent root = FXMLLoader.load(getClass().getResource(FXML_MAIN_FORM_TEMPLATE));
        primaryStage.setScene(new Scene(root, 700, 420));
        primaryStage.setResizable(false);
        addLogo(primaryStage);
        primaryStage.show();
    }
}
