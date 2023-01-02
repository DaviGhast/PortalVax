package controllers;

import database.DBAccess;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import server.RMIServer;

import javafx.scene.control.Button;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ServerCVController implements Initializable {

    public Label label_error;
    public Button button_startserver;
    public TextField et_username, et_password, et_nomedatabase, et_host, et_portadatabase ;
    public ImageView image;

    Label error = label_error;

    @FXML void startServerButton() {

        String username = et_username.getText();
        String password = et_password.getText();
        String nomedatabase = et_nomedatabase.getText();
        String portadatabase = et_portadatabase.getText();
        String host = et_host.getText();

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
                RMIServer.start();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Informazioni Errate");
                String s ="Alcuni dei campi inseriti risultano errati";
                alert.setContentText(s);
                alert.show();
            }

        }

    }

    public void CheckDBAccessAndStartServer() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));

        et_host.setPromptText("localhost");
        et_portadatabase.setPromptText("5432");
        et_nomedatabase.setPromptText("portalvaxdb");
    }
}
