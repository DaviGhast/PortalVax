package controllers;

import client.RMIClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.CentroVaccinale;
import model.Risposta;
import model.Stato;
import util.StyleUI;
import util.Validator;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RecuperoIDController implements Initializable {
    public TextField tf_cf, tf_result;
    public ImageView search;
    public DialogPane dialog;
    private String idVaccinazione, codiceFiscale;

    public void search(ActionEvent actionEvent) throws RemoteException {
        if (!tf_cf.getText().isEmpty()){
            if (Validator.codiceFiscale(tf_cf.getText())) {
                Risposta risposta = RMIClient.server.cercaIdVaccinazione(tf_cf.getText());

                if (risposta.getStato() == Stato.GOOD) {
                    idVaccinazione = String.valueOf(risposta.getObject());
                    codiceFiscale = tf_cf.getText();
                    tf_result.setText(idVaccinazione);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setHeaderText("Informazioni non Sufficienti");
                    alert.setContentText(risposta.getMessage());
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Informazioni non Sufficienti");
                alert.setContentText("Il Codice Fiscale inserito non è corretto");
                alert.show();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.setImage(new Image("images/search.png"));

    }

    public String getIdVaccinazione() {
        return idVaccinazione;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }
}
