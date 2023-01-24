
package controllers;

import Interface.RMIServerInterface;
import client.RMIClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;

/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
/**
 * La classe contenente il controller del main client della parte UI
 */
public class MainClientUIController extends Application {

    private static Scene scene;

    private static FXMLLoader fxmlLoader;

    private static String backScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(loadFXML("client_home"));
        //scene = new Scene(loadFXML("home"));
        //scene.setFill(Color.TRANSPARENT);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.getIcons().add(new Image("images/logo_portalvax.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("PortalVax Client - Portale Vaccinale");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Close");
            alert.setHeaderText("Chiusura Programma");
            String s = "Sei sicuro di vuoler chiudere il programma?";
            alert.setContentText(s);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                try {
                    stop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            } else {
                event.consume();
            }
        });
        primaryStage.show();
   }

   static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
   }

   private static Parent loadFXML(String fxml) throws IOException {
       fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(MainClientUIController.class.getClassLoader().getResource("fxml/"+fxml+".fxml"));
       return fxmlLoader.load();
   }

    public static String getBackScene() {
        return backScene;
    }

    public static void setBackScene(String backScene) {
        MainClientUIController.backScene = backScene;
    }

    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

}
