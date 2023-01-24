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
import model.CittadinoRegistrato;
import model.Risposta;
import model.Stato;
import util.AlgoritmoMD5;
import util.StyleUI;
import util.Validator;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
/**
 * La classe contenente il controller del login del cittadino della parte UI
 */
public class LoginCittadinoController implements Initializable {
    
    /**
     * oggetti TextField
     */
    public TextField tf_emailuserid;
    
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
    public ImageView image, cross_email, checkmark_email, info_email, checkmark_password, cross_password, info_password;
    /**
     * oggetti PasswordField
     */
    public PasswordField password;
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location è un parametro di base del metodo
     * param resources è un parametro di base del metodo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        image.setImage(new Image("images/banner.png"));

        info_email.setImage(new Image("images/information.png"));
        cross_email.setImage(new Image("images/cross.png"));
        checkmark_email.setImage(new Image("images/check_mark.png"));
        cross_email.setVisible(false);
        checkmark_email.setVisible(false);

        info_password.setImage(new Image("images/information.png"));
        cross_password.setImage(new Image("images/cross.png"));
        checkmark_password.setImage(new Image("images/check_mark.png"));
        cross_password.setVisible(false);
        checkmark_password.setVisible(false);

    }

    /**
     * Il metodo <code>login</code> prende i dati dalla view e effettua chiamata del metodo login del server per 
     * verificare le informazioni per l'accesso e restituisce a schermo il risultato
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo
     */
    public void login(ActionEvent actionEvent) throws IOException {

        if (!tf_emailuserid.getText().isEmpty() & !password.getText().isEmpty()) {

            Risposta risposta = RMIClient.server.loginCittadino(tf_emailuserid.getText(),
                    AlgoritmoMD5.converti(password.getText()));

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
            alert.setHeaderText("Esito Login");
            alert.setContentText(risposta.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent()) {
                // l'alert esiste, nessun bottone premuto
            } else if(result.get() == ButtonType.OK & risposta.getStato() == Stato.GOOD) {
                MainClientUIController.setRoot("cittadino_registrato_home");
                CittadinoRegistratoHomeController cittadinoRegistratoHomeController =
                        MainClientUIController.getFxmlLoader().getController();
                cittadinoRegistratoHomeController.inflateUI((String) risposta.getObject());
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
     * @param mouseEvent oggetto di tipo MouseEvent
     */
    public void viewRegex0(MouseEvent mouseEvent) {
        infoRegex.setText("");
    }
    /**
     * Il metodo <code>viewRegexEmail</code> permettono di visualizzare a schermo la regola usata per validare il campo Email
     * @param mouseEvent oggetto di tipo MouseEvent
     */
    public void viewRegexEmail(MouseEvent mouseEvent) {
        infoRegex.setText("Email: inserire da 2 a 30 caratteri");
    }
    /**
     * Il metodo <code>viewRegexPassword</code> permettono di visualizzare a schermo la regola usata per validare il campo Password
     * @param mouseEvent oggetto di tipo MouseEvent
     */
    public void viewRegexPassword(MouseEvent mouseEvent) {
        infoRegex.setText("Password: inserire da 2 a 30 caratteri");
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
}
