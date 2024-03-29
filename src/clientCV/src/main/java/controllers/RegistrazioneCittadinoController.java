package controllers;

import client.RMIClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.*;
import util.AlgoritmoMD5;
import util.StyleUI;
import util.Validator;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
/**
 * La classe contenente il controller della registrazione dei cittadini della parte UI
 */
public class RegistrazioneCittadinoController implements Initializable {

    /**
     * oggetti TextField
     */
    public TextField tf_idvaccinazione, tf_email, tf_userid, tf_codicefiscale;
    /**
     * oggetto Label
     */
    public Label  infoRegex;
    /**
     * oggetti Button
     */
    public Button button_registracittadino, indietro;
    /**
     * oggetti ImageView
     */
    public ImageView image, search, cross_idvaccinazione, checkmark_idvaccinazione, info_idvaccinazione,
            checkmark_codicefiscale, cross_codicefiscale, info_codicefiscale, cross_email, checkmark_email,
            info_email, checkmark_userid, cross_userid, info_userid, checkmark_password, cross_password, info_password,
            checkmark_confermapassword, cross_confermapassword;
    /**
     * oggetti PasswordField
     */
    public PasswordField password, conferma_password;
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location è un parametro di base del metodo
     * param resources è un parametro di base del metodo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        image.setImage(new Image("images/banner.png"));
        search.setImage(new Image("images/search.png"));

        info_idvaccinazione.setImage(new Image("images/information.png"));
        cross_idvaccinazione.setImage(new Image("images/cross.png"));
        checkmark_idvaccinazione.setImage(new Image("images/check_mark.png"));
        cross_idvaccinazione.setVisible(false);
        checkmark_idvaccinazione.setVisible(false);

        info_codicefiscale.setImage(new Image("images/information.png"));
        cross_codicefiscale.setImage(new Image("images/cross.png"));
        checkmark_codicefiscale.setImage(new Image("images/check_mark.png"));
        cross_codicefiscale.setVisible(false);
        checkmark_codicefiscale.setVisible(false);

        info_email.setImage(new Image("images/information.png"));
        cross_email.setImage(new Image("images/cross.png"));
        checkmark_email.setImage(new Image("images/check_mark.png"));
        cross_email.setVisible(false);
        checkmark_email.setVisible(false);

        info_userid.setImage(new Image("images/information.png"));
        cross_userid.setImage(new Image("images/cross.png"));
        checkmark_userid.setImage(new Image("images/check_mark.png"));
        cross_userid.setVisible(false);
        checkmark_userid.setVisible(false);

        info_password.setImage(new Image("images/information.png"));
        cross_password.setImage(new Image("images/cross.png"));
        checkmark_password.setImage(new Image("images/check_mark.png"));
        cross_password.setVisible(false);
        checkmark_password.setVisible(false);

        cross_confermapassword.setImage(new Image("images/cross.png"));
        checkmark_confermapassword.setImage(new Image("images/check_mark.png"));
        cross_confermapassword.setVisible(false);
        checkmark_confermapassword.setVisible(false);

    }

    /**
     * avvia il popup per recuperare id vaccinazione
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void viewSearch(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainClientUIController.class.getClassLoader().getResource("fxml/id_vaccinazione.fxml"));
        DialogPane pane = fxmlLoader.load();
        
        RecuperoIDController recuperoIDController = fxmlLoader.getController();

        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setDialogPane(pane);
        dialog.setTitle("Recupera ID Vaccinazione");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) {
            String idVaccinazione = recuperoIDController.getIdVaccinazione();
            if (!idVaccinazione.isEmpty()){
                tf_idvaccinazione.setText(idVaccinazione);
                tf_codicefiscale.setText(recuperoIDController.getCodiceFiscale());
            }
        }

    }

    /**
     * controlla i dati inseriti nella view, chiama il metodo registra cittadino del server e visualizza il risultato
      * @param actionEvent evento azione view
     * @throws IOException io exception
     */
    public void registraCittadino(ActionEvent actionEvent) throws IOException {

        tf_email.setText(tf_email.getText().toLowerCase());
        tf_userid.setText(tf_userid.getText().toLowerCase());

        CittadinoRegistrato cittadinoRegistrato = new CittadinoRegistrato();
        
        if (validatorCodiceFiscale()) {

            cittadinoRegistrato.setCodiceFiscale(tf_codicefiscale.getText());
            cittadinoRegistrato.setEmail(tf_email.getText());
            cittadinoRegistrato.setUserId(tf_userid.getText());
            cittadinoRegistrato.setPassword(AlgoritmoMD5.converti(password.getText()));

            Risposta risposta = RMIClient.server.registraCittadino(cittadinoRegistrato,Short.parseShort(tf_idvaccinazione.getText()));

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
            alert.setHeaderText("Registrazione Vaccinato");
            alert.setContentText(risposta.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent()) {
                // l'alert esiste, nessun bottone premuto
            } else if(result.get() == ButtonType.OK & risposta.getStato() == Stato.GOOD) {
                MainClientUIController.setRoot("cittadino_registrato_home");
                CittadinoRegistratoHomeController cittadinoRegistratoHomeController =
                        MainClientUIController.getFxmlLoader().getController();
                cittadinoRegistratoHomeController.inflateUI(cittadinoRegistrato.getUserId());
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Informazioni non Sufficienti");
            String s ="Controlla e Compila tutti i campi presenti nel form";
            alert.setContentText(s);
            alert.show();
        }
    }
    /**
     * Il metodo <code>viewRegex0</code> nasconde il messaggio della regola  prima reso visibile
     * @param mouseEvent evento mouse
     */
    public void viewRegex0(MouseEvent mouseEvent) {
        infoRegex.setText("");
    }
    /**
     * Il metodo <code>viewRegexIDVaccinazione</code> permettono di visualizzare a schermo la regola usata per validare il campo ID Vaccinazione
     * @param mouseEvent evento mouse
     */
    public void viewRegexIDVaccinazione(MouseEvent mouseEvent) {
        infoRegex.setText("ID Vaccinazione: inserisci o recuperalo cliccando la lente di ricerca");
    }
    /**
     * Il metodo <code>viewRegexEmail</code> permettono di visualizzare a schermo la regola usata per validare il campo Email
     * @param mouseEvent evento mouse
     */
    public void viewRegexEmail(MouseEvent mouseEvent) {
        infoRegex.setText("Email: inserire da 2 a 30 caratteri");
    }
    /**
     * Il metodo <code>viewRegexUserId</code> permettono di visualizzare a schermo la regola usata per validare il campo UserID
     * @param mouseEvent evento mouse
     */
    public void viewRegexUserId(MouseEvent mouseEvent) {
        infoRegex.setText("UserID: inserire da 2 a 30 caratteri");
    }
    /**
     * Il metodo <code>viewRegexPassword</code> permettono di visualizzare a schermo la regola usata per validare il campo Password
     * @param mouseEvent evento mouse
     */
    public void viewRegexPassword(MouseEvent mouseEvent) {
        infoRegex.setText("Password: inserire da 2 a 30 caratteri");
    }
    /**
     * Il metodo <code>viewRegexCodiceFiscale</code> permettono di visualizzare a schermo la regola usata per validare il campo Codice Fiscale
     * @param mouseEvent evento mouse
     */
    public void viewRegexCodiceFiscale(MouseEvent mouseEvent) {
        infoRegex.setText("Codice Fiscale: Inserire 16 caratteri alfanumerici");
    }
    /**
     * Il metodo <code>torna_indietro</code> richiama il metodo setRoot e permette di spostarsi alla finestra cittadino_home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("cittadino_home");
    }

    /**
     * validatore campo codice fiscale
     * @return risultato vaerifica
     */
    public boolean validatorCodiceFiscale() {
        if (Validator.codiceFiscale(tf_codicefiscale.getText())) {
            cross_codicefiscale.setVisible(false);
            checkmark_codicefiscale.setVisible(true);
            StyleUI.removeRed(tf_codicefiscale);
            StyleUI.setGreen(tf_codicefiscale);
            return true;
        } else {
            checkmark_codicefiscale.setVisible(false);
            cross_codicefiscale.setVisible(true);
            StyleUI.removeGreen(tf_codicefiscale);
            StyleUI.setRed(tf_codicefiscale);
            return false;
        }
    }


    /**
     * validatore campo idvaccinazione
     * @param keyEvent evento tastiera
     * @return risultato vaerifica
     */
    public boolean validatorIdVaccinazione(KeyEvent keyEvent) {
        if (!tf_idvaccinazione.getText().isEmpty()) {
            cross_idvaccinazione.setVisible(false);
            checkmark_idvaccinazione.setVisible(true);
            StyleUI.removeRed(tf_idvaccinazione);
            StyleUI.setGreen(tf_idvaccinazione);
            return true;
        } else {
            checkmark_idvaccinazione.setVisible(false);
            cross_idvaccinazione.setVisible(true);
            StyleUI.removeGreen(tf_idvaccinazione);
            StyleUI.setRed(tf_idvaccinazione);
            return false;
        }
    }

    /**
     * validatore campo email
     * @param keyEvent evento tastiera
     * @return risultato vaerifica
     */
    public boolean validatorEmail(KeyEvent keyEvent) {
        if (Validator.email(tf_email.getText())) {
            cross_email.setVisible(false);
            checkmark_email.setVisible(true);
            StyleUI.removeRed(tf_email);
            StyleUI.setGreen(tf_email);
            return true;
        } else {
            checkmark_email.setVisible(false);
            cross_email.setVisible(true);
            StyleUI.removeGreen(tf_email);
            StyleUI.setRed(tf_email);
            return false;
        }
    }

    /**
     * validatore campo userid
     * @param keyEvent evento tastiera
     * @return risultato vaerifica
     */
    public boolean validatorUserID(KeyEvent keyEvent) {
        if (Validator.userId(tf_userid.getText())) {
            cross_userid.setVisible(false);
            checkmark_userid.setVisible(true);
            StyleUI.removeRed(tf_userid);
            StyleUI.setGreen(tf_userid);
            return true;
        } else {
            checkmark_userid.setVisible(false);
            cross_userid.setVisible(true);
            StyleUI.removeGreen(tf_userid);
            StyleUI.setRed(tf_userid);
            return false;
        }
    }

    /**
     * validatore campo password
     * @param keyEvent evento tastiera
     * @return risultato vaerifica
     */
    public boolean validatorPassword(KeyEvent keyEvent) {
        if (Validator.password(password.getText())) {
            cross_password.setVisible(false);
            checkmark_password.setVisible(true);
            StyleUI.removeRed(password);
            StyleUI.setGreen(password);
            return true;
        } else {
            checkmark_password.setVisible(false);
            cross_password.setVisible(true);
            StyleUI.removeGreen(password);
            StyleUI.setRed(password);
            return false;
        }
    }

    /**
     * validatore campo conferma pwd
     * @param keyEvent evento tastiera
     * @return risultato vaerifica
     */
    public boolean validatorConfermaPassword(KeyEvent keyEvent) {
        if (conferma_password.getText().equals(password.getText())) {
            cross_confermapassword.setVisible(false);
            checkmark_confermapassword.setVisible(true);
            StyleUI.removeRed(conferma_password);
            StyleUI.setGreen(conferma_password);
            return true;
        } else {
            checkmark_confermapassword.setVisible(false);
            cross_confermapassword.setVisible(true);
            StyleUI.removeGreen(conferma_password);
            StyleUI.setRed(conferma_password);
            return false;
        }
    }
}
