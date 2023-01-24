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
public class SearchController implements Initializable {
    public TextField tf_nomecentro;
    public ImageView search;
    public DialogPane dialog;
    private CentroVaccinale centroVaccinale = null;
    public TableView<CentroVaccinale> table;
    public TableColumn<CentroVaccinale,String> nome;
    public TableColumn<CentroVaccinale,String> tipologia;
    public TableColumn<CentroVaccinale,String> comune;
    public Label info_selected;

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

    public void clickItem(MouseEvent mouseEvent) {

        centroVaccinale = table.getSelectionModel().getSelectedItem();

        if (centroVaccinale != null) {
            info_selected.setText("Hai Selezionato: " + centroVaccinale.getNomeCentroVaccinale());
            StyleUI.setGreen(info_selected);
        }

    }

    public CentroVaccinale getCentroVaccinale() {
        return centroVaccinale;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.setImage(new Image("images/search.png"));

        nome.setCellValueFactory(new PropertyValueFactory("nomeCentroVaccinale"));
        tipologia.setCellValueFactory(new PropertyValueFactory("tipologia"));
        comune.setCellValueFactory(new PropertyValueFactory("comune"));
    }
}
