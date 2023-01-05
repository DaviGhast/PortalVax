package controllers;

import client.RMIClient;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.CentroVaccinale;
import model.Cittadino;
import model.Risposta;
import model.Vaccinazione;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;

public class RegistraVaccinatoController implements Initializable {



    public ChoiceBox<String> choicebox_nomecentro, choicebox_tipovaccino;
    public TextField tf_nome, tf_cognome, tf_codicefiscale;

    public DatePicker datepicker_data;

    public Button button_addnewvaccinato;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choicebox_nomecentro.getItems().addAll();
        choicebox_tipovaccino.getItems().addAll();

    }

    public void RegistraNuovoVaccinato(ActionEvent actionEvent) throws RemoteException {

        Cittadino nuovocittadino = new Cittadino();
        Vaccinazione nuovavaccinazione = new Vaccinazione();

        String data = datepicker_data.getPromptText();
        String nomecentro = choicebox_nomecentro.getValue();
        String tipovaccino = choicebox_tipovaccino.getValue();
        String nome = tf_nome.getText();
        String cognome = tf_cognome.getText();
        String codicefiscale = tf_codicefiscale.getText();

        if (data.isEmpty()||nomecentro.isEmpty()||tipovaccino.isEmpty()||nome.isEmpty()||cognome.isEmpty()||codicefiscale.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Informazioni non Sufficienti");
            String s ="Controlla e Compila tutti i campi presenti nel form";
            alert.setContentText(s);
            alert.show();

        } else {

            nuovocittadino.setCodiceFiscale(codicefiscale);
            nuovocittadino.setCognomeCittadino(cognome);
            nuovocittadino.setNomeCittadino(nome);

            nuovavaccinazione.setCodiceFiscale(codicefiscale);
            nuovavaccinazione.setVaccinoSomministrato(tipovaccino);
            nuovavaccinazione.setDataVaccinazione(data);
            //nuovavaccinazione.setId();

            RMIClient.server.registraVaccinato(nuovavaccinazione, nuovocittadino);
        }
    }
}
