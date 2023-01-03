package controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class OperatoreHomeController {
    public void RegistraNuovoCentro(ActionEvent actionEvent) throws IOException {
        MainUIController.setRoot("registra_centro_vaccinale.fxml");
    }

    public void RegistraNuovoVaccinato(ActionEvent actionEvent) {
    }
}
