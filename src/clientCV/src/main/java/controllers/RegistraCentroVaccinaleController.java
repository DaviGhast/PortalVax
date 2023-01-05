package controllers;

import client.RMIClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import model.CentroVaccinale;

import javax.xml.bind.ValidationException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import util.StyleUI;

public class RegistraCentroVaccinaleController implements Initializable {
    public ChoiceBox<String> choicebox_tipologia;
    public TextField tf_comune, tf_nomecentro, tf_cap, tf_provincia, tf_indirizzo;
    public ImageView image, cross_nomecentro, cross_comune, cross_indirizzo, cross_provincia, cross_cap, checkmark_nomecentro, checkmark_indirizzo, checkmark_comune, checkmark_provincia, checkmark_cap;
    public Button button_addnewcenter;
    public Label infoRegex;
    public ImageView info_nomecentro;

    public boolean validatorfield1() {
        if (Pattern.matches("^[a-zA-Z0-9 ,.'-]{2,50}", tf_nomecentro.getText())) {
            cross_nomecentro.setVisible(false);
            checkmark_nomecentro.setVisible(true);
            StyleUI.removeRed(tf_nomecentro);
            StyleUI.setGreen(tf_nomecentro);
            return true;
        } else {
            checkmark_nomecentro.setVisible(false);
            cross_nomecentro.setVisible(true);
            StyleUI.removeGreen(tf_nomecentro);
            StyleUI.setRed(tf_nomecentro);
            return false;
        }
    }

    public boolean validatorfield2() {
        if (Pattern.matches("^[a-zA-Z0-9 ,.'-]{2,255}", tf_indirizzo.getText())) {
            cross_indirizzo.setVisible(false);
            checkmark_indirizzo.setVisible(true);
            return true;
        } else {
            checkmark_indirizzo.setVisible(false);
            cross_indirizzo.setVisible(true);
            return false;
        }
    }

    public boolean validatorfield3() {
        if (Pattern.matches("^[a-zA-Z ']{2,50}", tf_comune.getText())) {
            cross_comune.setVisible(false);
            checkmark_comune.setVisible(true);
            return true;
        } else {
            checkmark_comune.setVisible(false);
            cross_comune.setVisible(true);
            return false;
        }
    }

    public boolean validatorfield4() {
        if (Pattern.matches("^[a-zA-Z]{2}", tf_provincia.getText())) {
            cross_provincia.setVisible(false);
            checkmark_provincia.setVisible(true);
            return true;
        } else {
            checkmark_provincia.setVisible(false);
            cross_provincia.setVisible(true);
            return false;
        }
    }

    public boolean validatorfield5() {
        if (Pattern.matches("^[0-9]{5}", tf_cap.getText())) {
            cross_cap.setVisible(false);
            checkmark_cap.setVisible(true);
            return true;
        } else {
            checkmark_cap.setVisible(false);
            cross_cap.setVisible(true);
            return false;
        }
    }


    public void RegistraNuovoCentro(ActionEvent actionEvent) throws RemoteException {

        CentroVaccinale nuovocentro = new CentroVaccinale();

        if (validatorfield1() & validatorfield2() & validatorfield3() & validatorfield4() & validatorfield5()) {

            nuovocentro.setNomeCentroVaccinale(tf_nomecentro.getText());
            nuovocentro.setSiglaProvincia(tf_provincia.getText());
            nuovocentro.setComune(tf_comune.getText());
            nuovocentro.setCap(Integer.parseInt(tf_cap.getText()));
            nuovocentro.setIndirizzo(tf_indirizzo.getText());
            nuovocentro.setTipologia(choicebox_tipologia.getValue());
            RMIClient.server.registraCentroVaccinale(nuovocentro);

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Informazioni non Sufficienti");
            String s = "Controlla e Compila tutti i campi presenti nel form";
            alert.setContentText(s);
            alert.show();
        }
    }

    public void viewRegex0() {
        infoRegex.setText(" ");
    }
    public void viewRegex1() {
        infoRegex.setText("Nome Centro: Inserire da 2 a 50 caratteri");
    }

    public void viewRegex2() {
        infoRegex.setText("Indirizzo: Inserire da 2 a 255 caratteri");
    }

    public void viewRegex3() {
        infoRegex.setText("Comune: Inserire da 2 a 50 caratteri");
    }

    public void viewRegex4() {
        infoRegex.setText("Provincia: Inserire 2 caratteri alfabetici");
    }

    public void viewRegex5() {
        infoRegex.setText("CAP: Inserire 5 caratteri numerici");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            image.setImage(new Image("images/banner.png"));

            info_nomecentro.setImage(new Image("images/information.png"));
            cross_nomecentro.setImage(new Image("images/cross.png"));
            checkmark_nomecentro.setImage(new Image("images/check_mark.png"));
            cross_nomecentro.setVisible(false);
            checkmark_nomecentro.setVisible(false);

            String[] tipologie = RMIClient.server.getTipologie();
            choicebox_tipologia.setItems((FXCollections.observableArrayList(tipologie)));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}






