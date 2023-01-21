package controllers;

import client.RMIClient;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class OperatoreHomeController implements Initializable {
    public Button button_1;
    public Button button_2;
    public ImageView image;

    public void registraNuovoCentro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("registra_centro_vaccinale");
    }

    public void registraNuovoVaccinato(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("registra_vaccinato");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));
    }

    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("home");
    }
}
