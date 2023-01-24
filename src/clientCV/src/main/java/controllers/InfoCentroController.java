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
import model.CentroVaccinale;
import model.ReportEventoAvverso;
import model.Risposta;
import model.Stato;
import util.FixInput;
import util.StyleUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class InfoCentroController implements Initializable {

    public ImageView image, search;
    public ChoiceBox<String> choicebox_ricerca, choicebox_tipologia;
    public TableView<CentroVaccinale> table;
    public TableColumn<CentroVaccinale,String> nome;
    public TableColumn<CentroVaccinale,String> provincia;
    public TableColumn<CentroVaccinale,String> tipologia;
    public TableColumn<CentroVaccinale,String> indirizzo;
    public TableColumn<CentroVaccinale,String> comune;
    public Text promt_text, promt_tipologia;
    public TextField textfield;
    private String userid;
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));
        search.setImage(new Image("images/search.png"));

        choicebox_ricerca.setItems((FXCollections.observableArrayList("Nome","Comune","Comune e Tipologia")));
        try {
            String[] tipologie = RMIClient.server.getTipologie();
            choicebox_tipologia.setItems((FXCollections.observableArrayList(tipologie)));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        nome.setCellValueFactory(new PropertyValueFactory("nomeCentroVaccinale"));
        indirizzo.setCellValueFactory(new PropertyValueFactory("indirizzo"));
        comune.setCellValueFactory(new PropertyValueFactory("comune"));
        provincia.setCellValueFactory(new PropertyValueFactory("siglaProvincia"));
        tipologia.setCellValueFactory(new PropertyValueFactory("tipologia"));


        textfield.setDisable(true);
        choicebox_tipologia.setDisable(true);

    }

    /**
     * Il metodo <code>torna_indietro</code> richiama il metodo setRoot e poi il metodo getBackScene che permette di spostarsi alla finestra cittadino_registrato_home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @see MainClientUIController #setBackScene(String)
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot(MainClientUIController.getBackScene());
        if (MainClientUIController.getBackScene().equals("cittadino_registrato_home")) {
            CittadinoRegistratoHomeController cittadinoRegistratoHomeController =
                    MainClientUIController.getFxmlLoader().getController();
            cittadinoRegistratoHomeController.inflateUI(userid);
        }
    }

    /**
     * Il metodo <code>setRicerca</code> apre la finestra che permette di fare la ricerca
     * @param actionEvent oggetto di tipo ActionEvent
     */
    public void setRicerca(ActionEvent actionEvent) {
        switch (choicebox_ricerca.getValue()) {
            case "Nome":
                promt_text.setText("Nome");
                textfield.setDisable(false);
                choicebox_tipologia.setDisable(true);
                break;
            case "Comune":
                promt_text.setText("Comune");
                textfield.setDisable(false);
                choicebox_tipologia.setDisable(true);
                break;
            case "Comune e Tipologia":
                promt_text.setText("Comune");
                textfield.setDisable(false);
                choicebox_tipologia.setDisable(false);
                break;
            default:
                break;
        }
    }

    /**
     * Il metodo <code>cerca</code> prende i dati dalla view ed effetua la chiamata di ricerca al server
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws RemoteException esclude tutte le eccezioni che possono verificarsi
     */
    public void cerca(ActionEvent actionEvent) throws RemoteException {
        Risposta risposta = new Risposta();

        if (choicebox_ricerca.getValue() != null) {
            if (!textfield.getText().isEmpty()) {
                if (choicebox_ricerca.getValue().equals("Comune e Tipologia") & choicebox_tipologia.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setHeaderText("Informazioni non Sufficienti");
                    alert.setContentText("Il Campiìo di Ricerca \"Tipologia\" non può essere nullo");
                    alert.show();
                } else {
                    switch (choicebox_ricerca.getValue()) {
                        case "Nome":
                            risposta = RMIClient.server.cercaCentroVaccinale(textfield.getText());
                            break;
                        case "Comune":
                            textfield.setText(FixInput.tuttePrimeLettereMaiuscole(textfield.getText()));
                            risposta = RMIClient.server.cercaCentroVaccinale(textfield.getText(), "");
                            break;
                        case "Comune e Tipologia":
                            textfield.setText(FixInput.tuttePrimeLettereMaiuscole(textfield.getText()));
                            risposta = RMIClient.server.cercaCentroVaccinale(textfield.getText(), choicebox_tipologia.getValue());
                            break;
                    }

                    if (risposta.getStato() == Stato.GOOD){
                        ArrayList<CentroVaccinale> centriVaccinali = (ArrayList<CentroVaccinale>) risposta.getObject();
                        ObservableList<CentroVaccinale> observableList = FXCollections.observableArrayList();
                        observableList.addAll(centriVaccinali);
                        table.setItems(observableList);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Errore");
                        alert.setHeaderText("Informazioni non Sufficienti");
                        alert.setContentText(risposta.getMessage());
                        alert.show();
                    }
                }
            } else {
                if (choicebox_ricerca.getValue().equals("Comune e Tipologia") & choicebox_tipologia.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setHeaderText("Informazioni non Sufficienti");
                    alert.setContentText("I Campi di Ricerca non possono essere nulli");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("WARNING");
                    alert.setHeaderText("Informazioni non Sufficienti");
                    alert.setContentText("Il Campo di Ricerca non può essere nullo");
                    alert.show();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errore");
            alert.setHeaderText("Informazioni non Sufficienti");
            alert.setContentText("Devi Scegliere almeno una tipologia di ricerca");
            alert.show();
        }

    }

    /**
     * Il metodo <code>clickItem</code> Prendere le informazioni del centro vaccinale selezionato all'interno della tabella,
     * effettuata la richiesta dei report degli eventi per quel centro al server e avvia il il popup per visualizzare i dati restituiti dal server
     * @param mouseEvent oggetto di tipo MouseEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void clickItem(MouseEvent mouseEvent) throws IOException {
        CentroVaccinale centroVaccinale = table.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainClientUIController.class.getClassLoader().getResource("fxml/report_eventi_avversi.fxml"));
        DialogPane pane = fxmlLoader.load();

        ReportEventiAvversiController reportEventiAvversiController = fxmlLoader.getController();
        if (centroVaccinale != null) {
            try {
                Risposta risposta = RMIClient.server.visulizzaInfoCentroVaccinale(centroVaccinale);
                if (risposta.getStato() == Stato.GOOD){
                    ArrayList<ReportEventoAvverso> centriVaccinali = (ArrayList<ReportEventoAvverso>) risposta.getObject();
                    reportEventiAvversiController.inflateUI(centriVaccinali);

                    Alert dialog = new Alert(Alert.AlertType.NONE);
                    dialog.setDialogPane(pane);
                    dialog.setTitle("Report Anonimo Eventi Avversi");
                    dialog.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setHeaderText("Informazioni non Sufficienti");
                    alert.setContentText(risposta.getMessage());
                    alert.showAndWait();
                }

            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }


    }
    /**
     * Il metodo <code>inflateUI</code> permette di passare l'informazione al controller
     * @param userid stringa contenente l'info da passare
     */
    public void inflateUI(String userid) {
        this.userid = userid;
    }
}
