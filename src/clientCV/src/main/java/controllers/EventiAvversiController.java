package controllers;

import client.RMIClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EventiAvversiController implements Initializable {

    public ImageView image;
    public TableView<EventoAvverso> table;
    public TableColumn<EventoAvverso,String> evento;
    public TableColumn<EventoAvverso,Byte> severita;
    public TableColumn<EventoAvverso,String> note;
    public Label titolo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));

        evento.setCellValueFactory(new PropertyValueFactory("evento"));
        severita.setCellValueFactory(new PropertyValueFactory("severita"));
        note.setCellValueFactory(new PropertyValueFactory("note"));

    }

    public void inflateUI(String userId) throws RemoteException {

        titolo.setText(titolo.getText() + userId);

        Risposta risposta = RMIClient.server.visualizzaEventiAvversi(userId);
        if (risposta.getStato() == Stato.GOOD) {
            ArrayList<EventoAvverso> eventiAvversi = (ArrayList<EventoAvverso>) risposta.getObject();
            ObservableList<EventoAvverso> observableList = FXCollections.observableArrayList();
            observableList.addAll(eventiAvversi);
            table.setItems(observableList);
        }

    }

    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot(MainClientUIController.getBackScene());
    }

    public void inserisciEvento(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainClientUIController.class.getClassLoader().getResource("fxml/inserisci_evento.fxml"));
        DialogPane pane = fxmlLoader.load();

        InserisciEventoController inserisciEventoController = fxmlLoader.getController();

        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setDialogPane(pane);
        dialog.setTitle("Recupera ID Vaccinazione");
        ButtonType inserisci = new ButtonType("Inserisci Evento", ButtonBar.ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().add(inserisci);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) {
            EventoAvverso eventoAvverso = inserisciEventoController.getEventoAvverso();
            if (eventoAvverso != null){
                Risposta risposta = RMIClient.server.inserisciEventiAvversi(eventoAvverso);

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
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Informazioni non Sufficienti");
                String s ="Evento non inserito per mancanza di dati provare a ritentare l'inserimento";
                alert.setContentText(s);
                alert.show();
            }
        }
    }
}
