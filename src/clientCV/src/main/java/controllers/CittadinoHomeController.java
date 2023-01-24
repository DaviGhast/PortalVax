package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.CentroVaccinale;
import model.CittadinoRegistrato;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class CittadinoHomeController implements Initializable {

    public ImageView image;
    public Button indietro, button_infocentro, button_idvaccinazione, button_registrati, button_login;

    /**
     * Il metodo <code>infoCentro</code>richiama il metodo setRoot e permette di spostarsi alla finestra infoCentro {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @see MainClientUIController #setBackScene(String)
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void infoCentro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("infocentro");
        MainClientUIController.setBackScene("cittadino_home");
    }

    /**
     * Il metodo <code>registraCittadino</code>richiama il metodo setRoot e permette di spostarsi alla finestra registrazione_cittadino {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void registraCittadino(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("registrazione_cittadino");
    }

    /**
     *Il metodo <code>recuperaIDVaccinazione</code> avvia il popup per recuperare l'id di vaccinazione
     * @param actionEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void recuperaIDVaccinazione(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainClientUIController.class.getClassLoader().getResource("fxml/id_vaccinazione.fxml"));
        DialogPane pane = fxmlLoader.load();

        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setDialogPane(pane);
        dialog.setTitle("Recupera ID Vaccinazione");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.showAndWait();

    }
    /**
     * Il metodo <code>torna_indietro</code>richiama il metodo setRoot e permette di spostarsi alla finestra home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("home");
    }
    /**
     * Il metodo <code>login</code>richiama il metodo setRoot e permette di spostarsi alla finestra login_cittadino {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void login(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("login_cittadino");
    }

    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));
    }
}
