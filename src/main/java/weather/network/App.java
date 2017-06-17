package weather.network;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;



public class App extends Application
{

    private static final String[] LOGOS = {"/image/icon16.png", "/image/icon24.png", "/image/icon32.png"}; //zrodlo ikonek
    private static final String FXML_MAIN_FORM_TEMPLATE = "/fxml/weather-main.fxml";
    private static final int WIDTH__WINDOW=700;
    private static final int HEIGHT_WINDOW=420;

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
        primaryStage.setScene(new Scene(root, WIDTH__WINDOW ,HEIGHT_WINDOW));
        primaryStage.setResizable(false);
        addLogo(primaryStage);
        primaryStage.show();
    }
}
