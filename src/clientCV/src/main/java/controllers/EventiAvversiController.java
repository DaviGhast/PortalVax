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
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
/**
 * La classe contenente il controller degli Eventi Avversi della parte UI
 */
public class EventiAvversiController implements Initializable {

    /**
     * oggetti ImageView
     */
    public ImageView image;
    /**
     * oggetto TableView
     */
    public TableView<EventoAvverso> table;
    /**
     * oggetto TableColumn
     */
    public TableColumn<EventoAvverso,String> evento;
    /**
     * oggetto TableColumn
     */
    public TableColumn<EventoAvverso,Byte> severita;
    /**
     * oggetto TableColumn
     */
    public TableColumn<EventoAvverso,String> note;
    /**
     * oggetto Label
     */
    public Label  titolo;
    /**
     * oggetto String
     */
    private String userId;
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location è un parametro di base del metodo
     * param resources è un parametro di base del metodo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));

        evento.setCellValueFactory(new PropertyValueFactory("evento"));
        severita.setCellValueFactory(new PropertyValueFactory("severita"));
        note.setCellValueFactory(new PropertyValueFactory("note"));

    }
    /**
     * Il metodo <code>inflateUI</code> permette di passare l'informazione al controller
     * @param userId stringa contenente l'info da passare
     */
    public void inflateUI(String userId) throws RemoteException {

        this.userId = userId;
        titolo.setText(titolo.getText() + userId);

        Risposta risposta = RMIClient.server.visualizzaEventiAvversi(userId);
        if (risposta.getStato() == Stato.GOOD) {
            ArrayList<EventoAvverso> eventiAvversi = (ArrayList<EventoAvverso>) risposta.getObject();
            ObservableList<EventoAvverso> observableList = FXCollections.observableArrayList();
            observableList.addAll(eventiAvversi);
            table.setItems(observableList);
        }

    }
    /**
     * Il metodo <code>torna_indietro</code>richiama il metodo setRoot e permette di spostarsi alla finestra cittadino_registrato_home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("cittadino_registrato_home");
        CittadinoRegistratoHomeController cittadinoRegistratoHomeController =
                MainClientUIController.getFxmlLoader().getController();
        cittadinoRegistratoHomeController.inflateUI(userId);
    }
    /**
     * Il metodo <code>inserisciEvento</code> ti permette di accedere alla finestra per inserire un nuovo evento {@link InserisciEventoController}
     * @see InserisciEventoController inserisciEventoController
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void inserisciEvento(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainClientUIController.class.getClassLoader().getResource("fxml/inserisci_evento.fxml"));
        DialogPane pane = fxmlLoader.load();

        InserisciEventoController inserisciEventoController = fxmlLoader.getController();

        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setDialogPane(pane);
        dialog.setTitle("Inserisci Evento Avverso");
        ButtonType inserisci = new ButtonType("Inserisci Evento", ButtonBar.ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().add(inserisci);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) {
            EventoAvverso eventoAvverso = inserisciEventoController.getEventoAvverso();
            if (eventoAvverso != null){
                eventoAvverso.setIdCittadino(userId);
                Risposta risposta = RMIClient.server.inserisciEventiAvversi(eventoAvverso);

                Alert alert = null;
                switch (risposta.getStato()) {
                    case GOOD:
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        inflateUI(userId);
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
