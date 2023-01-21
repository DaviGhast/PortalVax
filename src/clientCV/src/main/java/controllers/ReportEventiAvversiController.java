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

public class ReportEventiAvversiController implements Initializable {
    public TextField tf_nomecentro;
    public ImageView search;
    public DialogPane dialog;
    public TableView<ReportEventoAvverso> table;
    public TableColumn<ReportEventoAvverso,String> evento;
    public TableColumn<ReportEventoAvverso,Integer> segnalazioni;
    public TableColumn<ReportEventoAvverso,Double> severita;
    public Label info_selected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        evento.setCellValueFactory(new PropertyValueFactory("evento"));
        segnalazioni.setCellValueFactory(new PropertyValueFactory("numeroSegnalazioni"));
        severita.setCellValueFactory(new PropertyValueFactory("severitaMedia"));

    }

    public void inflateUI(ArrayList<ReportEventoAvverso> centriVaccinali) {

        ObservableList<ReportEventoAvverso> observableList = FXCollections.observableArrayList();
        observableList.addAll(centriVaccinali);
        table.setItems(observableList);

    }
}
