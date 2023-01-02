
package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class MainUIController extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(loadFXML("Welcome"));
        //scene.setFill(Color.TRANSPARENT);
        scene.setFill(Color.DARKGREEN);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.getIcons().add(new Image("images/logo_uninsubria.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Portale Vaccinale +Immuni");
        primaryStage.setResizable(false);
        primaryStage.show();
   }

   static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
   }

   private static Parent loadFXML(String fxml) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(MainUIController.class.getClassLoader().getResource("fxml/"+fxml+".fxml"));
       return fxmlLoader.load();
   }
    
}
