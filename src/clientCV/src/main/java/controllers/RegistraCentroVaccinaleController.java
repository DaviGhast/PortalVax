package controllers;

import client.RMIClient;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegistraCentroVaccinaleController implements Initializable {
    public ChoiceBox<String> choicebox_tipologia;
    public TextField tf_comune, tf_nomecentro, tf_cap, tf_provincia, tf_indirizzo;
    public ImageView image;
    public Button button_addnewcenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            choicebox_tipologia.getItems().addAll(RMIClient.server.getTipologie());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void RegistraNuovoCentro(ActionEvent actionEvent) {
        String tipologiascelta = choicebox_tipologia.getValue();
        String comune = tf_comune.getText();
        String indirizzo = tf_indirizzo.getText();
        String cap = tf_cap.getText();
        String provincia = tf_provincia.getText();
        String nomecentro = tf_nomecentro.getText();

    }

}
