package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.ReportEventoAvverso;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
/**
 * La classe contenente il controller dei report degli eventi avversi della parte UI
 */
public class ReportEventiAvversiController implements Initializable {
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
     * oggetto TableView
     */
    public TableView<ReportEventoAvverso> table;
    /**
     * oggetto TableColumn
     */
    public TableColumn<ReportEventoAvverso,String> evento;
    /**
     * oggetto TableColumn
     */
    public TableColumn<ReportEventoAvverso,Integer> segnalazioni;
    /**
     * oggetto TableColumn
     */
    public TableColumn<ReportEventoAvverso,Double> severita;
    /**
     * oggetto Label
     */
    public Label  info_selected;
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location è un parametro di base del metodo
     * param resources è un parametro di base del metodo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        evento.setCellValueFactory(new PropertyValueFactory("evento"));
        segnalazioni.setCellValueFactory(new PropertyValueFactory("numeroSegnalazioni"));
        severita.setCellValueFactory(new PropertyValueFactory("severitaMedia"));

    }

    /**
     * Il metodo <code>inflateUI</code> permette di passare l'informazione al controller
     * @param centriVaccinali lista di oggetti ReportEventoAvverso
     */
    public void inflateUI(ArrayList<ReportEventoAvverso> centriVaccinali) {

        ObservableList<ReportEventoAvverso> observableList = FXCollections.observableArrayList();
        observableList.addAll(centriVaccinali);
        table.setItems(observableList);

    }
}
