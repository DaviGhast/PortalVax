package controllers;

import database.DBAccess;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.RMIServer;

import java.awt.*;
import javafx.scene.control.Button;
import java.rmi.RemoteException;

public class ServerCVController {

    public Label label_error;
    public Button button_startserver;
    public TextField et_username, et_password, et_nomedatabase, et_host, et_portadatabase ;



    Label error = label_error;

    @FXML void StartServerButton() throws RemoteException {

        String username = et_username.getText();
        String password = et_password.getText();
        String nomedatabase = et_nomedatabase.getText();
        String portadatabase = et_portadatabase.getText();
        String host = et_host.getText();

        if(username.isEmpty()||password.isEmpty()||host.isEmpty()||portadatabase.isEmpty()||nomedatabase.isEmpty()) {
            error.setText("Compila tutti i campi presenti");

        } else {
            DBAccess.setHost(host);
            DBAccess.setHost(username);
            DBAccess.setHost(password);
            DBAccess.setHost(nomedatabase);
            DBAccess.setHost(portadatabase);
            DBAccess.getConnect();

            if(DBAccess.getValid()){
                RMIServer.start();
            } else {
                error.setText("Alcuni dei campi inseriti risultano errati");
            }

        }

    }
}
