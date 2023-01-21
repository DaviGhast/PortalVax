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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CittadinoHomeController implements Initializable {

    public ImageView image;
    public Button indietro, button_infocentro, button_idvaccinazione, button_registrati, button_login;

    public void infoCentro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("infocentro");
        MainClientUIController.setBackScene("cittadino_home");
    }

    public void registraCittadino(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("registrazione_cittadino");
    }

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

    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("home");
    }

    public void login(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));
    }
}
