package controllers;

import database.DBAccess;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.RMIServer;

import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * La classe di interfaccia <code>ServerDBAccessController</code> permette di effettuare il collegamento al database
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class ServerDBAccessController implements Initializable {

    /**
     * oggetti TextField
     */
    public TextField et_username, et_password, et_nomedatabase, et_host, et_portadatabase ;
    /**
     * oggetti ImageView
     */
    public ImageView image;
    /**
     * oggetti Button
     */
    public Button button_login_db;

    /**
     * metodo che effettua i controlli dei campi input e poi avvia la prova di connessione al DB
     */
    @FXML void loginDB() {

        String username = et_username.getText();
        String password = et_password.getText();
        String nomedatabase = et_nomedatabase.getText();
        String portadatabase = et_portadatabase.getText();
        String host = et_host.getText();

        if (username.isEmpty()) {
            et_username.setText(et_username.getPromptText());
            username = et_host.getText();
        }
        if (host.isEmpty()) {
            et_host.setText(et_host.getPromptText());
            host = et_host.getText();
        }
        if (portadatabase.isEmpty()) {
            et_portadatabase.setText(et_portadatabase.getPromptText());
            portadatabase = et_portadatabase.getText();
        }
        if (nomedatabase.isEmpty()) {
            et_nomedatabase.setText(et_nomedatabase.getPromptText());
            nomedatabase = et_nomedatabase.getText();
        }

        if (username.isEmpty()||password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Informazioni non Sufficienti");
            String s ="Controlla e Compila tutti i campi presenti nel form";
            alert.setContentText(s);
            alert.show();

        } else {
            DBAccess.setHost(host);
            DBAccess.setUsername(username);
            DBAccess.setPassword(password);
            DBAccess.setNomedatabase(nomedatabase);
            DBAccess.setPorta(portadatabase);
            DBAccess.getConnect();

            if(DBAccess.getValid()){
                Stage stage = (Stage) button_login_db.getScene().getWindow();
                stage.close();
            }

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

        et_username.setPromptText("postgres");
        et_host.setPromptText("localhost");
        et_portadatabase.setPromptText("5432");
        et_nomedatabase.setPromptText("portalvaxdb");
    }
}
