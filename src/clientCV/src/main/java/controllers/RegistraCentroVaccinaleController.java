package controllers;

import Interface.RMIServerInterface;
import centrivaccinali.MainClient;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistraCentroVaccinaleController implements Initializable {
    public ChoiceBox<String> choicebox_tipologia;
    public TextField tf_comune, tf_nomecentro, tf_cap, tf_provincia, tf_indirizzo;
    private String[] tipologie = {"Ospedaliero","Aziendale","Hub"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choicebox_tipologia.getItems().addAll(tipologie);
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
