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
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class CittadinoRegistratoHomeController extends CittadinoHomeController implements Initializable {

    public ImageView image;
    public Button button_logout, button_infocentro, button_idvaccinazione, button_eventi;
    public Text tf_userid;
    private String userid;
    /**
     * Il metodo <code>infoCentro</code> richiama il metodo setRoot e permette di spostarsi alla finestra infocentro {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @see MainClientUIController #setBackScene(String)
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void infoCentro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("infocentro");
        InfoCentroController infoCentroController =
                MainClientUIController.getFxmlLoader().getController();
        infoCentroController.inflateUI(userid);
        MainClientUIController.setBackScene("cittadino_registrato_home");
    }
    /**
     * Il metodo <code>logout</code> richiama il metodo setRoot e permette di spostarsi alla finestra cittadino_home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void logout(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("cittadino_home");
    }
    /**
     * Il metodo <code>eventiAvversi</code> richiama il metodo setRoot e permette di spostarsi alla finestra eventi_avversi {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni che possono verificarsi
     */
    public void eventiAvversi(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("eventi_avversi");
        EventiAvversiController eventiAvversiController =
                MainClientUIController.getFxmlLoader().getController();
        eventiAvversiController.inflateUI(userid);
    }

    /**
     * Il metodo <code>inflateUI</code> permette di passare l'informazione al controller
     * @param userId stringa contenente l'info da passare
     */
    public void inflateUI(String userId) {
        this.userid = userId;
        tf_userid.setText(userId);
    }
}
