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
import util.StyleUI;
import util.Validator;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegistrazioneCittadinoController implements Initializable {

    public TextField tf_idvaccinazione, tf_email, tf_userid, tf_codicefiscale;
    public Label infoRegex;
    public Button button_registracittadino, indietro;
    public ImageView image, search, cross_idvaccinazione, checkmark_idvaccinazione, info_idvaccinazione,
            checkmark_codicefiscale, cross_codicefiscale, info_codicefiscale, cross_email, checkmark_email,
            info_email, checkmark_userid, cross_userid, info_userid, checkmark_password, cross_password, info_password,
            checkmark_confermapassword, cross_confermapassword;
    public PasswordField password, conferma_password;

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

    public void registraCittadino(ActionEvent actionEvent) throws IOException {

        CittadinoRegistrato cittadinoRegistrato = new CittadinoRegistrato();
        
        if (validatorCodiceFiscale()) {

            cittadinoRegistrato.setCodiceFiscale(tf_codicefiscale.getText());
            cittadinoRegistrato.setEmail(tf_email.getText());
            cittadinoRegistrato.setUserId(tf_userid.getText());
            cittadinoRegistrato.setPassword(password.getText());

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
                // alert is exited, no button has been pressed.
            } else if(result.get() == ButtonType.OK & risposta.getStato() == Stato.GOOD) {
                MainClientUIController.setRoot("cittadino_registrato_home");
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

    public void viewRegex0(MouseEvent mouseEvent) {
        infoRegex.setText("");
    }

    public void viewRegexNomeCentro(MouseEvent mouseEvent) {
        infoRegex.setText("Nome Centro: inserisci o cerca il nome di un centro esistente");
    }

    public void viewRegex1(MouseEvent mouseEvent) {
        infoRegex.setText("Nome vaccinato: inserire da 2 a 30 caratteri");
    }

    public void viewRegex2(MouseEvent mouseEvent) {
        infoRegex.setText("Cognome vaccinato: inserire da 2 a 30 caratteri");
    }

    public void viewRegex3(MouseEvent mouseEvent) {
        infoRegex.setText("Codice Fiscale: Inserire 16 caratteri alfanumerici");
    }

    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("operatore_home");
    }

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


    public void validatorIdVaccinazione(KeyEvent keyEvent) {
    }

    public void validatorfield2(KeyEvent keyEvent) {
    }

    public void validatorfield3(KeyEvent keyEvent) {
    }
}
