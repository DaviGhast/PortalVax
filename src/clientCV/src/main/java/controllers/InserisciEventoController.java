package controllers;

import client.RMIClient;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.EventoAvverso;
import util.Validator;

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
public class InserisciEventoController implements Initializable {

    public DialogPane dialog;
    public ChoiceBox choisebox_evento, choisebox_severita;
    public ImageView info_evento, cross_evento, checkmark_evento, info_severita, cross_severita, checkmark_severita,
            info_note, cross_note, checkmark_note;
    public Text count_down;
    public Label infoRegex;
    public TextArea ta_note;
    /**
     * Il metodo <code>initialize</code> permette di inizializare la finestra
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        info_evento.setImage(new Image("images/information.png"));
        cross_evento.setImage(new Image("images/cross.png"));
        checkmark_evento.setImage(new Image("images/check_mark.png"));
        cross_evento.setVisible(false);
        checkmark_evento.setVisible(false);

        info_severita.setImage(new Image("images/information.png"));
        cross_severita.setImage(new Image("images/cross.png"));
        checkmark_severita.setImage(new Image("images/check_mark.png"));
        cross_severita.setVisible(false);
        checkmark_severita.setVisible(false);

        info_note.setImage(new Image("images/information.png"));
        cross_note.setImage(new Image("images/cross.png"));
        checkmark_note.setImage(new Image("images/check_mark.png"));
        cross_note.setVisible(false);
        checkmark_note.setVisible(false);

        String[] eventi = new String[0];
        try {
            eventi = RMIClient.server.getEventi();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        choisebox_evento.setItems((FXCollections.observableArrayList(eventi)));

        choisebox_severita.setItems((FXCollections.observableArrayList(1,2,3,4,5)));
    }

    /**
     * Il metodo <code>getEventoAvverso</code> prende i dati dalla view e  restituisce un evento avverso generato con quei dati
     * @return oggetto di tipo EventoAvverso
     */
    public EventoAvverso getEventoAvverso() {
        EventoAvverso eventoAvverso = null;
        if (validatorChoiseBoxEvento() & validatorChoiseBoxSeverita() & validatorNote()) {
            eventoAvverso = new EventoAvverso();
            eventoAvverso.setEvento(String.valueOf(choisebox_evento.getValue()));
            eventoAvverso.setSeverita(Byte.parseByte(String.valueOf(choisebox_severita.getValue())));
            eventoAvverso.setNote(ta_note.getText());
        }
        return eventoAvverso;
    }

    /**
     * Il metodo <code>viewRegex0</code> nasconde il messaggio della regola  prima reso visibile
     * @param mouseEvent oggetto di tipo MouseEvent
     */
    public void viewRegex0(MouseEvent mouseEvent) {
        infoRegex.setText("");
    }
    /**
     * Il metodo <code>validatorNote</code> verifica che il campo Note abbia una lunghezza maggiore o uguale a 0 e minore o uguale a 255

     */
    public boolean validatorNote() {
        int max = 256;
        int length = ta_note.getText().length();
        int count = max -length;
        count_down.setText(String.valueOf(count));
        if (count >= 0 & count <= 256) {
            cross_note.setVisible(false);
            checkmark_note.setVisible(true);
            return true;
        } else {
            checkmark_note.setVisible(false);
            cross_note.setVisible(true);
            return false;
        }
    }
    /**
     * Il metodo <code>validatorChoiseBoxEvento</code> chiama il metodo descritto dentro la classe validator {@link Validator}
     * @see Validator #choiseBox(ChoiseBox, ImageView, ImageView)
     */
    public boolean validatorChoiseBoxEvento() {
        return Validator.choiseBox(choisebox_evento,cross_evento,checkmark_evento);
    }
    /**
     * Il metodo <code>validatorChoiseBoxEvento</code> chiama il metodo descritto dentro la classe validator {@link Validator}
     * @see Validator #choiseBox(ChoiseBox, ImageView, ImageView)
     */
    public boolean validatorChoiseBoxSeverita() {
        return Validator.choiseBox(choisebox_severita,cross_severita,checkmark_severita);
    }

    /**
     * Il metodo <code>viewRegexEvento</code> permettono di visualizzare a schermo la regola usata per validare il campo Nome Centro
     * @param mouseEvent oggetto di tipo MouseEvent
     */
    public void viewRegexEvento(MouseEvent mouseEvent) {
        infoRegex.setText("Nome Centro: cerca il nome di un centro esistente cliccando sulla lente di ricerca");
    }
    /**
     * Il metodo <code>viewRegexSeverita</code> permettono di visualizzare a schermo la regola usata per validare il campo Nome Centro
     * @param mouseEvent oggetto di tipo MouseEvent
     */
    public void viewRegexSeverita(MouseEvent mouseEvent) {
        infoRegex.setText("Nome Centro: cerca il nome di un centro esistente cliccando sulla lente di ricerca");
    }
    /**
     * Il metodo <code>viewRegexNote</code> permettono di visualizzare a schermo la regola usata per validare il campo Nome Centro
     * @param mouseEvent oggetto di tipo MouseEvent
     */
    public void viewRegexNote(MouseEvent mouseEvent) {
        infoRegex.setText("Nome Centro: cerca il nome di un centro esistente cliccando sulla lente di ricerca");
    }
}
