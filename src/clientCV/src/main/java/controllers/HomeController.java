package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
/**
 * La classe contenente il controller del Home della parte UI
 */
public class HomeController implements Initializable {
    /**
     * oggetti Button
     */
    public Button button_op;
    /**
     * oggetti ImageView
     */
    public ImageView image;
    /**
     * oggetti Button
     */
    public Button button_citt;
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location è un parametro di base del metodo
     * param resources è un parametro di base del metodo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));
    }
    /**
     * Il metodo <code>operatoreAction</code> richiama il metodo setRoot e permette di spostarsi alla finestra operatore_home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void operatoreAction(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("operatore_home");
    }
    /**
     * Il metodo <code>cittadinoAction</code> richiama il metodo setRoot e permette di spostarsi alla finestra cittadino_home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void cittadinoAction(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("cittadino_home");
    }
}
