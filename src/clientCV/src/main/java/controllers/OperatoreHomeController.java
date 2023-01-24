package controllers;

import client.RMIClient;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
/**
 * La classe contenente il controller del operatore home della parte UI
 */
public class OperatoreHomeController implements Initializable {
    /**
     * oggetti ImageView
     */
    public ImageView image;
    /**
     * Il metodo <code>registraNuovoCentro</code> richiama il metodo setRoot e permette di spostarsi alla finestra registra_centro_vaccinale {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void registraNuovoCentro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("registra_centro_vaccinale");
    }
    /**
     * Il metodo <code>registraNuovoVaccinato</code> richiama il metodo setRoot e permette di spostarsi alla finestra registra_vaccinato {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void registraNuovoVaccinato(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("registra_vaccinato");
    }
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
     * Il metodo <code>torna_indietro</code> richiama il metodo setRoot e permette di spostarsi alla finestra home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("home");
    }
}
