
package controllers;

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
import server.RMIServer;

import java.io.IOException;
import java.util.Optional;

/**
 * La classe <code>MainServerUIController</code> gestisce la finestra dell'interfaccia grafica
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class MainServerUIController extends Application {

    /**
     * oggetto scena
     */
    private static Scene scene;

    /**
     *Il Metodo <code>start</code> avvia la finestra dell'intefaccia grafica e carica la prima pagina
     * @param primaryStage la finestra dell'interfaccia grafica
     * @throws Exception esclude tutte le eccezioni che possono verificarsi in questo metodo
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(loadFXML("server_home"));
        //scene.setFill(Color.TRANSPARENT);
        scene.setFill(Color.DARKGREEN);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.getIcons().add(new Image("images/logo_portalvax.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("PortalVax Server - Portale Vaccinale");
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


    /**
     * Il Metodo <code>setRoot</code> cambia la pagina della finestra dell'interfaccia
     * @param fxml è il nome della finestra da impostare
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni di input/output che possono verificarsi nel metodo
     */
   static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
   }

    /**
     *Il Metodo <code>loadFXML</code> carico il file FXML
     * @param fxml è il nome del file FXML da caricare
     * @return il caricamento del file
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni di input/output che possono verificarsi nel metodo
     */
   private static Parent loadFXML(String fxml) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(MainServerUIController.class.getClassLoader().getResource("fxml/"+fxml+".fxml"));
       return fxmlLoader.load();
   }
    
}
