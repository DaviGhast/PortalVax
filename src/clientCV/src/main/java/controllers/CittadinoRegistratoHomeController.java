package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.ReportEventoAvverso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CittadinoRegistratoHomeController extends CittadinoHomeController implements Initializable {

    public ImageView image;
    public Button button_logout, button_infocentro, button_idvaccinazione, button_eventi;
    public Text tf_userid;
    private String userid;

    public void infoCentro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("infocentro");
        MainClientUIController.setBackScene("cittadino_registrato_home");
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("home");
    }

    public void eventiAvversi(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("eventi_avversi");
        EventiAvversiController eventiAvversiController =
                MainClientUIController.getFxmlLoader().getController();
        eventiAvversiController.inflateUI(userid);
    }

    public void inflateUI(String userId) {
        this.userid = userId;
        tf_userid.setText(userId);
    }
}
