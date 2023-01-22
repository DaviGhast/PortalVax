
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
 * La classe <code>MainServerUIController</code> gestisce la finestra dell'interfaccia grafica
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class MainServerUIController extends Application {

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
        primaryStage.show();
   }


    /**
     * Il Metodo <code>setRoot</code> cambia la pagina della finestra dell'interfaccia
     * @param fxml è il nome della finestra da impostare
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo
     */
   static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
   }

    /**
     *Il Metodo <code>loadFXML</code> carico il file FXML
     * @param fxml è il nome del file FXML da caricare
     * @return il caricamento del file
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo
     */
   private static Parent loadFXML(String fxml) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(MainServerUIController.class.getClassLoader().getResource("fxml/"+fxml+".fxml"));
       return fxmlLoader.load();
   }
    
}
