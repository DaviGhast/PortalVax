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

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;
import java.util.regex.Pattern;

import model.Risposta;
import util.FixInput;
import util.StyleUI;
import util.Validator;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
/**
 * La classe contenente il controller della registrazione dei centri vaccinali della parte UI
 */
public class RegistraCentroVaccinaleController implements Initializable {
    /**
     * oggetti ChoiceBox
     */
    public ChoiceBox<String> choicebox_tipologia;
    /**
     * oggetti TextField
     */
    public TextField tf_comune, tf_nomecentro, tf_cap, tf_provincia, tf_indirizzo;
    /**
     * oggetti ImageView
     */
    public ImageView image, cross_nomecentro, cross_comune, cross_indirizzo, cross_provincia, cross_cap,
            checkmark_nomecentro, checkmark_indirizzo, checkmark_comune, checkmark_provincia, checkmark_cap,
            cross_tipologia, checkmark_tipologia;
    /**
     * oggetti Button
     */
    public Button button_addnewcenter;
    /**
     * oggetto Label
     */
    public Label  infoRegex;
    /**
     * oggetti ImageView
     */
    public ImageView info_nomecentro, info_indirizzo, info_comune, info_provincia, info_cap;

    public boolean validatorChoiseBox() {
        return Validator.choiseBox(choicebox_tipologia,cross_tipologia,checkmark_tipologia);
    }

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
        return Validator.textField(Validator.indirizzo(tf_indirizzo.getText()),
                tf_indirizzo,cross_indirizzo,checkmark_indirizzo);
    }

    public boolean validatorfield3() {
        if (Pattern.matches("^[a-zA-Z ']{2,50}", tf_comune.getText())) {
            cross_comune.setVisible(false);
            checkmark_comune.setVisible(true);
            StyleUI.removeRed(tf_comune);
            StyleUI.setGreen(tf_comune);
            return true;
        } else {
            checkmark_comune.setVisible(false);
            cross_comune.setVisible(true);
            StyleUI.removeGreen(tf_comune);
            StyleUI.setRed(tf_comune);
            return false;
        }
    }

    public boolean validatorfield4() {
        if (Pattern.matches("^[a-zA-Z]{2}", tf_provincia.getText())) {
            cross_provincia.setVisible(false);
            checkmark_provincia.setVisible(true);
            StyleUI.removeRed(tf_provincia);
            StyleUI.setGreen(tf_provincia);
            return true;
        } else {
            checkmark_provincia.setVisible(false);
            cross_provincia.setVisible(true);
            StyleUI.removeGreen(tf_provincia);
            StyleUI.setRed(tf_provincia);
            return false;
        }
    }

    public boolean validatorfield5() {
        return Validator.textField(Validator.cap(tf_cap.getText()),tf_cap,cross_cap,checkmark_cap);
    }


    /**
     * controlla i dati inseriti nella view, chiama il metodo registra centro del server e visualizza il risultato
     * @param actionEvent evento azione view
     * @throws IOException io exception
     */
    public void registraNuovoCentro(ActionEvent actionEvent) throws IOException {

        tf_nomecentro.setText(FixInput.tuttePrimeLettereMaiuscole(FixInput.aggiungiSpazi(tf_nomecentro.getText())));
        tf_indirizzo.setText(FixInput.tuttePrimeLettereMaiuscole(FixInput.aggiungiSpazi(tf_indirizzo.getText())));
        tf_comune.setText(FixInput.tuttePrimeLettereMaiuscole(tf_comune.getText()));
        tf_provincia.setText(tf_provincia.getText().toUpperCase());

        CentroVaccinale nuovocentro = new CentroVaccinale();

        if (validatorChoiseBox() & validatorfield1() & validatorfield2() & validatorfield3() & validatorfield4() &
                validatorfield5()) {

            nuovocentro.setNomeCentroVaccinale(tf_nomecentro.getText());
            nuovocentro.setSiglaProvincia(tf_provincia.getText());
            nuovocentro.setComune(tf_comune.getText());
            nuovocentro.setCap(Integer.parseInt(tf_cap.getText()));
            nuovocentro.setIndirizzo(tf_indirizzo.getText());
            nuovocentro.setTipologia(choicebox_tipologia.getValue());

            Risposta risposta = RMIClient.server.registraCentroVaccinale(nuovocentro);

            Alert alert = null;
            switch (risposta.getStato()) {
                case GOOD:
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    break;
                case ERROR:
                    alert = new Alert(Alert.AlertType.WARNING);
                    break;
                case BAD:
                    alert = new Alert(Alert.AlertType.ERROR);
                    break;
            }
            alert.setTitle(String.valueOf(risposta.getStato()));
            alert.setHeaderText("Registrazione Centro vaccinale");
            alert.setContentText(risposta.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent()) {
                // l'alert esiste, nessun bottone premuto
            } else if(result.get() == ButtonType.OK) {
                MainClientUIController.setRoot("operatore_home");
            }

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Informazioni non Sufficienti");
            String s = "Controlla e Compila tutti i campi presenti nel form";
            alert.setContentText(s);
            alert.show();
        }
    }
    /**
     * Il metodo <code>viewRegex0</code> nasconde il messaggio della regola  prima reso visibile
     */
    public void viewRegex0() {
        infoRegex.setText(" ");
    }
    /**
     * Il metodo <code>viewRegex1</code> permettono di visualizzare a schermo la regola usata per validare il campo Nome Centro
     */
    public void viewRegex1() {
        infoRegex.setText("Nome Centro: Inserire da 2 a 50 caratteri");
    }
    /**
     * Il metodo <code>viewRegex2</code> permettono di visualizzare a schermo la regola usata per validare il campo Indirizzo
     */
    public void viewRegex2() {
        infoRegex.setText("Indirizzo: Inserire indirizzo (qualificatore via/v.le/p.za/ecc.., nome, numero civico)");
    }
    /**
     * Il metodo <code>viewRegex3</code> permettono di visualizzare a schermo la regola usata per validare il campo Comune
     */
    public void viewRegex3() {
        infoRegex.setText("Comune: Inserire da 2 a 50 caratteri");
    }
    /**
     * Il metodo <code>viewRegex4</code> permettono di visualizzare a schermo la regola usata per validare il campo Provincia
     */
    public void viewRegex4() {
        infoRegex.setText("Provincia: Inserire 2 caratteri alfabetici");
    }
    /**
     * Il metodo <code>viewRegex5</code> permettono di visualizzare a schermo la regola usata per validare il campo CAP
     */
    public void viewRegex5() {
        infoRegex.setText("CAP: Inserire 5 caratteri numerici");
    }
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location è un parametro di base del metodo
     * param resources è un parametro di base del metodo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            image.setImage(new Image("images/banner.png"));

            cross_tipologia.setImage(new Image("images/cross.png"));
            checkmark_tipologia.setImage(new Image("images/check_mark.png"));
            cross_tipologia.setVisible(false);
            checkmark_tipologia.setVisible(false);

            info_nomecentro.setImage(new Image("images/information.png"));
            cross_nomecentro.setImage(new Image("images/cross.png"));
            checkmark_nomecentro.setImage(new Image("images/check_mark.png"));
            cross_nomecentro.setVisible(false);
            checkmark_nomecentro.setVisible(false);

            info_indirizzo.setImage(new Image("images/information.png"));
            cross_indirizzo.setImage(new Image("images/cross.png"));
            checkmark_indirizzo.setImage(new Image("images/check_mark.png"));
            cross_indirizzo.setVisible(false);
            checkmark_indirizzo.setVisible(false);

            info_comune.setImage(new Image("images/information.png"));
            cross_comune.setImage(new Image("images/cross.png"));
            checkmark_comune.setImage(new Image("images/check_mark.png"));
            cross_comune.setVisible(false);
            checkmark_comune.setVisible(false);

            info_provincia.setImage(new Image("images/information.png"));
            cross_provincia.setImage(new Image("images/cross.png"));
            checkmark_provincia.setImage(new Image("images/check_mark.png"));
            cross_provincia.setVisible(false);
            checkmark_provincia.setVisible(false);

            info_cap.setImage(new Image("images/information.png"));
            cross_cap.setImage(new Image("images/cross.png"));
            checkmark_cap.setImage(new Image("images/check_mark.png"));
            cross_cap.setVisible(false);
            checkmark_cap.setVisible(false);

            String[] tipologie = RMIClient.server.getTipologie();
            choicebox_tipologia.setItems((FXCollections.observableArrayList(tipologie)));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Il metodo <code>torna_indietro</code> richiama il metodo setRoot e permette di spostarsi alla finestra operatore_home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("operatore_home");
    }

}






