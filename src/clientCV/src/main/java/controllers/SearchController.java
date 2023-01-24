package controllers;

import client.RMIClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.CentroVaccinale;
import model.Risposta;
import model.Stato;
import util.StyleUI;

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
/**
 * La classe contenente il controller della ricerca della parte UI
 */
public class SearchController implements Initializable {
    /**
     * oggetti TextField
     */
    public TextField tf_nomecentro;
    /**
     * oggetti ImageView
     */
    public ImageView search;
    /**
     * oggetto DialogPane
     */
    public DialogPane dialog;
    /**
     * oggetto CentroVaccinale
     */
    private CentroVaccinale centroVaccinale = null;
    /**
     * oggetto TableView
     */
    public TableView<CentroVaccinale> table;
    /**
     * oggetto TableColumn
     */
    public TableColumn<CentroVaccinale,String> nome;
    /**
     * oggetto TableColumn
     */
    public TableColumn<CentroVaccinale,String> tipologia;
    /**
     * oggetto TableColumn
     */
    public TableColumn<CentroVaccinale,String> comune;
    /**
     * oggetto Label
     */
    public Label  info_selected;

    /**
     * controlla i campi di input e effettua la chiamata cercaCentroVaccinale al server
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws RemoteException esclude tutte le eccezioni <code>Remote</code> che possono verificarsi
     */
    public void search(ActionEvent actionEvent) throws RemoteException {
        if (!tf_nomecentro.getText().isEmpty()){
            Risposta risposta = RMIClient.server.cercaCentroVaccinale(tf_nomecentro.getText());

            if (risposta.getStato() == Stato.GOOD){
                ArrayList<CentroVaccinale> centriVaccinali = (ArrayList<CentroVaccinale>) risposta.getObject();
                ObservableList<CentroVaccinale> observableList = FXCollections.observableArrayList();
                observableList.addAll(centriVaccinali);
                table.setItems(observableList);
            }
        }
    }

    /**
     * prende dalla view il record selezionato nella tabella
     * @param mouseEvent oggetto di tipo MouseEvent
     */
    public void clickItem(MouseEvent mouseEvent) {

        centroVaccinale = table.getSelectionModel().getSelectedItem();

        if (centroVaccinale != null) {
            info_selected.setText("Hai Selezionato: " + centroVaccinale.getNomeCentroVaccinale());
            StyleUI.setGreen(info_selected);
        }

    }

    /**
     * restituisce il centro vaccinale selezionato
     * @return centro avccinale selezionato
     */
    public CentroVaccinale getCentroVaccinale() {
        return centroVaccinale;
    }
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location è un parametro di base del metodo
     * param resources è un parametro di base del metodo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.setImage(new Image("images/search.png"));

        nome.setCellValueFactory(new PropertyValueFactory("nomeCentroVaccinale"));
        tipologia.setCellValueFactory(new PropertyValueFactory("tipologia"));
        comune.setCellValueFactory(new PropertyValueFactory("comune"));
    }
}
