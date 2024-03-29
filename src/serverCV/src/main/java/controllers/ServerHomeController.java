package controllers;

import database.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.RMIServer;

import java.io.IOException;
import java.net.URL;
import java.rmi.NoSuchObjectException;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * La classe di interfaccia <code>ServerHomeController</code> permette di effettuare il collegamento al database
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class ServerHomeController implements Initializable {
    /**
     * E' un image view per il logo dell'applicazione e per i simboli di cross e checkmark
     */
    /**
     * oggetti ImageView
     */
    public ImageView image, cross_db, checkmark_db;

    /**
     * Sono i tipi dei bottoni
     */
    /**
     * oggetti Button
     */
    public Button button_db, button_stop, button_start, button_close;

    /**
     * Il metodo <code>loginDB</code> permette di effettuar eil login al data base
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo
     */
    public void loginDB(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainServerUIController.class.getClassLoader().getResource("fxml/server_db_access.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image("images/logo_portalvax.png"));
        stage.setScene(scene);
        stage.setTitle("PortalVax Server - DB Login");
        stage.setResizable(false);
        stage.showAndWait();

        if(DBAccess.getValid()){
            cross_db.setVisible(false);
            checkmark_db.setVisible(true);
            button_db.setDisable(true);
            button_start.setDisable(false);
        }

    }
    /**
     *Il Metodo <code>initialize</code> inizializza i componenti della pagina
     * @param location è un parametro di base del metodo è un parametro di base del metodo
     * param resources è un parametro di base del metodo è un parametro di base del metodo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));

        cross_db.setImage(new Image("images/cross.png"));
        checkmark_db.setImage(new Image("images/check_mark.png"));
        cross_db.setVisible(true);
        checkmark_db.setVisible(false);

    }

    /**
     * Il metodo <code>stopServer</code> stoppa il sevrer
     * @param actionEvent oggetto di tipo ActionEvent evento generato dalla view
     * @throws NoSuchObjectException esclude tutte le eccezioni <code>NoSuchObjectn</code> che possono verificarsi
     */
    public void stopServer(ActionEvent actionEvent) throws NoSuchObjectException {
        RMIServer.stop();
        button_stop.setDisable(true);
        button_start.setDisable(false);
    }
    /**
     * Il metodo <code>startServer</code> esegue il server
     * @param actionEvent oggetto di tipo ActionEvent evento generato dalla view
     */
    public void startServer(ActionEvent actionEvent) {
        RMIServer.start();
        RMIServer.checkEnum();
        button_start.setDisable(true);
        button_stop.setDisable(false);
    }
}
