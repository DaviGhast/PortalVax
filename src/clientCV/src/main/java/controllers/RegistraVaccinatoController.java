package controllers;

import client.RMIClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.*;
import util.FixInput;
import util.StyleUI;
import util.Validator;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
/**
 * La classe contenente il controller della registrazione dei vaccinati della parte UI
 */
public class RegistraVaccinatoController implements Initializable {

    public ChoiceBox<String> choicebox_tipovaccino;
    /**
     * oggetti TextField
     */
    public TextField tf_nomecentro, tf_nome, tf_cognome, tf_codicefiscale;

    public DatePicker datepicker_data;

    /**
     * oggetto Label
     */
    public Label  infoRegex;

    /**
     * oggetti Button
     */
    public Button button_addnewvaccinato;

    /**
     * oggetti ImageView
     */
    public ImageView image, cross_nome, checkmark_nome, cross_cognome, checkmark_cognome, cross_codicefiscale,
            checkmark_codicefiscale, info_nome, info_cognome, info_codicefiscale, cross_nomecentro,
            checkmark_nomecentro, cross_data, checkmark_data, cross_vaccino, checkmark_vaccino, search, info_nomecentro;
    /**
     * oggetti Button
     */
    public Button indietro;
    /**
     * oggetto CentroVaccinale
     */
   private CentroVaccinale centroVaccinaleScelto;

    /**
     * effettua una chiamata per una verifca lato server dell'esistenza del centro vaccinale inserito
     * @return risultato verifica
     * @throws RemoteException eccezione remote
     */
    public boolean validatorNomeCentroEsistente() throws RemoteException {
        Risposta risposta = RMIClient.server.esisteCentroVaccinale(tf_nomecentro.getText());

        if ((boolean) risposta.getObject()){
            cross_nomecentro.setVisible(false);
            checkmark_nomecentro.setVisible(true);
            StyleUI.removeRed(tf_nomecentro);
            StyleUI.setGreen(tf_nomecentro);
            return true;
        } else {
            checkmark_nomecentro.setVisible(false);
            cross_nomecentro.setVisible(true);
            StyleUI.removeGreen(tf_nomecentro);
            StyleUI.setRed(tf_nomecentro);
            return false;
        }

    }

    /**
     * validatore campo nome
     * @return risultato vaerifica
     */
    public boolean validatorfield1() {
        if (Pattern.matches("^[a-zA-Z]{2,30}", tf_nome.getText())) {
            cross_nome.setVisible(false);
            checkmark_nome.setVisible(true);
            StyleUI.removeRed(tf_nome);
            StyleUI.setGreen(tf_nome);
            return true;
        } else {
            checkmark_nome.setVisible(false);
            cross_nome.setVisible(true);
            StyleUI.removeGreen(tf_nome);
            StyleUI.setRed(tf_nome);
            return false;
        }
    }

    /**
     * validatore campo cognome
     * @return risultato vaerifica
     */
    public boolean validatorfield2() {
        if (Pattern.matches("^[a-zA-Z]{2,30}", tf_cognome.getText())) {
            cross_cognome.setVisible(false);
            checkmark_cognome.setVisible(true);
            StyleUI.removeRed(tf_cognome);
            StyleUI.setGreen(tf_cognome);
            return true;
        } else {
            checkmark_cognome.setVisible(false);
            cross_cognome.setVisible(true);
            StyleUI.removeGreen(tf_cognome);
            StyleUI.setRed(tf_cognome);
            return false;
        }
    }

    /**
     * validatore campo codice fiscale
     * @return risultato vaerifica
     */
    public boolean validatorfield3() {
        if (Validator.codiceFiscale(tf_codicefiscale.getText())) {
            cross_codicefiscale.setVisible(false);
            checkmark_codicefiscale.setVisible(true);
            StyleUI.removeRed(tf_codicefiscale);
            StyleUI.setGreen(tf_codicefiscale);
            return true;
        } else {
            checkmark_codicefiscale.setVisible(false);
            cross_codicefiscale.setVisible(true);
            StyleUI.removeGreen(tf_codicefiscale);
            StyleUI.setRed(tf_codicefiscale);
            return false;
        }
    }

    /**
     * validatore campo choicebox_tipovaccino
     * @return risultato vaerifica
     */
    public boolean validatorChoiseBox() {
        if (!String.valueOf(choicebox_tipovaccino.getSelectionModel().getSelectedItem()).equals("null")) {
            cross_vaccino.setVisible(false);
            checkmark_vaccino.setVisible(true);
            StyleUI.removeRed(choicebox_tipovaccino);
            StyleUI.setGreen(choicebox_tipovaccino);
            return true;
        } else {
            checkmark_vaccino.setVisible(false);
            cross_vaccino.setVisible(true);
            StyleUI.removeGreen(choicebox_tipovaccino);
            StyleUI.setRed(choicebox_tipovaccino);
            return false;
        }
    }

    /**
     * validatore campo datepicker_data
     * @return risultato vaerifica
     */
    public boolean validatorDate() {
        if (!String.valueOf(datepicker_data.getValue()).equals("null")) {
            cross_data.setVisible(false);
            checkmark_data.setVisible(true);
            StyleUI.removeRed(datepicker_data);
            StyleUI.setGreen(datepicker_data);
            return true;
        } else {
            checkmark_data.setVisible(false);
            cross_data.setVisible(true);
            StyleUI.removeGreen(datepicker_data);
            StyleUI.setRed(datepicker_data);
            return false;
        }
    }

    /**
     * avvia il popup per cercare nome centro
     * @param actionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void viewSearch(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainClientUIController.class.getClassLoader().getResource("fxml/search.fxml"));
        DialogPane search_pane = fxmlLoader.load();

        SearchController searchController = fxmlLoader.getController();

        Alert search_dialog = new Alert(Alert.AlertType.NONE);
        search_dialog.setDialogPane(search_pane);
        search_dialog.setTitle("Cerca Centro Vaccinale");
        search_dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Optional<ButtonType> result = search_dialog.showAndWait();
        if (result.isPresent()) {
            CentroVaccinale centroVaccinale = searchController.getCentroVaccinale();
            if (centroVaccinale != null) {
                tf_nomecentro.setText(centroVaccinale.getNomeCentroVaccinale());
                if (validatorNomeCentroEsistente())
                    centroVaccinaleScelto = centroVaccinale;
            }
        }

    }

    /**
     * controlla i dati inseriti nella view, chiama il metodo registra vaccinato del server e visualizza il risultato
     * @param actionEvent evento azione view
     * @throws IOException io exception
     */
    public void registraNuovoVaccinato(ActionEvent actionEvent) throws IOException {

        tf_cognome.setText(FixInput.tuttePrimeLettereMaiuscole(FixInput.aggiungiSpazi(tf_cognome.getText())));
        tf_nome.setText(FixInput.tuttePrimeLettereMaiuscole(FixInput.aggiungiSpazi(tf_nome.getText())));

        Cittadino nuovocittadino = new Cittadino();
        Vaccinazione nuovavaccinazione = new Vaccinazione();

        if (validatorfield1() & validatorfield2() & validatorfield3() & validatorDate() & validatorChoiseBox()) {

            nuovocittadino.setCodiceFiscale(tf_codicefiscale.getText());
            nuovocittadino.setCognomeCittadino(tf_cognome.getText());
            nuovocittadino.setNomeCittadino(tf_nome.getText());

            nuovavaccinazione.setCodiceFiscale(tf_codicefiscale.getText());
            nuovavaccinazione.setVaccinoSomministrato(choicebox_tipovaccino.getValue());
            nuovavaccinazione.setDataVaccinazione(datepicker_data.getValue());
            nuovavaccinazione.setIdCentroVaccinale(centroVaccinaleScelto.getId());

            Risposta risposta = RMIClient.server.registraVaccinato(nuovavaccinazione, nuovocittadino);

            Alert alert = null;
            switch (risposta.getStato()) {
                case GOOD:
                    alert = new Alert(Alert.AlertType.INFORMATION);
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
            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent()) {
                // l'alert esiste, nessun bottone premuto
            } else if(result.get() == ButtonType.OK & risposta.getStato() == Stato.GOOD) {
                Risposta risposta1 = RMIClient.server.cercaIdVaccinazione(nuovocittadino.getCodiceFiscale());

                if (risposta1.getStato() == Stato.GOOD) {
                    String idVaccinazione = String.valueOf(risposta1.getObject());

                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("ID Vaccinazione");
                    alert1.setHeaderText("ID di vaccinazione generato");
                    alert1.setContentText("il cittadino "+nuovocittadino.getNomeCittadino()+" "+
                            nuovocittadino.getCognomeCittadino()+" è associato alla vaccinazione: "+idVaccinazione);
                    alert1.show();
                }
                MainClientUIController.setRoot("operatore_home");
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Informazioni non Sufficienti");
            String s ="Controlla e Compila tutti i campi presenti nel form";
            alert.setContentText(s);
            alert.show();
        }
    }
    /**
     * Il metodo <code>viewRegex0</code> nasconde il messaggio della regola  prima reso visibile
     */
    public void viewRegex0(MouseEvent mouseEvent) {
        infoRegex.setText("");
    }
    /**
     * Il metodo <code>viewRegexNomeCentro</code> permettono di visualizzare a schermo la regola usata per validare il campo Nome Centro
     */
    public void viewRegexNomeCentro(MouseEvent mouseEvent) {
        infoRegex.setText("Nome Centro: cerca il nome di un centro esistente cliccando sulla lente di ricerca");
    }
    /**
     * Il metodo <code>viewRegex1</code> permettono di visualizzare a schermo la regola usata per validare il campo Nome vaccinato
     */
    public void viewRegex1(MouseEvent mouseEvent) {
        infoRegex.setText("Nome vaccinato: inserire da 2 a 30 caratteri");
    }
    /**
     * Il metodo <code>viewRegex2</code> permettono di visualizzare a schermo la regola usata per validare il campo Cognome vaccinato
     */
    public void viewRegex2(MouseEvent mouseEvent) {
        infoRegex.setText("Cognome vaccinato: inserire da 2 a 30 caratteri");
    }
    /**
     * Il metodo <code>viewRegex3</code> permettono di visualizzare a schermo la regola usata per validare il campo Codice Fiscale
     */
    public void viewRegex3(MouseEvent mouseEvent) {
        infoRegex.setText("Codice Fiscale: Inserire 16 caratteri alfanumerici");
    }
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location è un parametro di base del metodo
     * param resources è un parametro di base del metodo
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        image.setImage(new Image("images/banner.png"));
        search.setImage(new Image("images/search.png"));

        info_nomecentro.setImage(new Image("images/information.png"));
        cross_nomecentro.setImage(new Image("images/cross.png"));
        checkmark_nomecentro.setImage(new Image("images/check_mark.png"));
        cross_nomecentro.setVisible(false);
        checkmark_nomecentro.setVisible(false);

        info_nome.setImage(new Image("images/information.png"));
        cross_nome.setImage(new Image("images/cross.png"));
        checkmark_nome.setImage(new Image("images/check_mark.png"));
        cross_nome.setVisible(false);
        checkmark_nome.setVisible(false);

        info_cognome.setImage(new Image("images/information.png"));
        cross_cognome.setImage(new Image("images/cross.png"));
        checkmark_cognome.setImage(new Image("images/check_mark.png"));
        cross_cognome.setVisible(false);
        checkmark_cognome.setVisible(false);

        info_codicefiscale.setImage(new Image("images/information.png"));
        cross_codicefiscale.setImage(new Image("images/cross.png"));
        checkmark_codicefiscale.setImage(new Image("images/check_mark.png"));
        cross_codicefiscale.setVisible(false);
        checkmark_codicefiscale.setVisible(false);

        cross_data.setImage(new Image("images/cross.png"));
        checkmark_data.setImage(new Image("images/check_mark.png"));
        cross_data.setVisible(false);
        checkmark_data.setVisible(false);

        cross_vaccino.setImage(new Image("images/cross.png"));
        checkmark_vaccino.setImage(new Image("images/check_mark.png"));
        cross_vaccino.setVisible(false);
        checkmark_vaccino.setVisible(false);

        String[] vaccini = new String[0];
        try {
            vaccini = RMIClient.server.getVaccini();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        choicebox_tipovaccino.setItems((FXCollections.observableArrayList(vaccini)));
    }
    /**
     * Il metodo <code>torna_indietro</code> richiama il metodo setRoot e permette di spostarsi alla finestra operatore_home {@link MainClientUIController}
     * @see MainClientUIController #setRoot(String)
     * @param actionEvent oggetto di tipo ActionEvent oggetto di tipo ActionEvent
     * @throws IOException esclude tutte le eccezioni di input/output che possono verificarsi nel metodo esclude tutte le eccezioni che possono verificarsi
     */
    public void torna_indietro(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("operatore_home");
    }
}
