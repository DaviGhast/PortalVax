package util;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.regex.Pattern;
/**
 * la Classe <code>Validator</code> fornisce metodi per validare i dati acquisiti dalle view grafiche e sottoposti a
 * controlli nelle relative classi di tipo <strong>Controller</strong>
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class Validator {

    /**
     * verifica attraverso una regexp se la stringa in input(codice fiscale) è valida
     * <p>
     *     la regexp per il codice fiscale verifica se la stringa contiene caratteri alfabetici maiuscoli,
     *     se contiene caratteri numerici e se è di 16 caratteri
     * </p>
     * @param string stringa in input per la verifica
     * @return boolean risultato dalla verifica
     */
    public static boolean codiceFiscale(String string) {
        return Pattern.matches("^[A-Z0-9]{16}", string);
    }

    /**
     * verifica attraverso una regexp se la stringa in input(nome e cognome) è valida
     * <p>
     *     la regexp verifica se la stringa contiene caratteri alfabetici e se la lunghezza è tra i 2 e i 30 caratteri
     * </p>
     * @param string stringa in input per la verifica
     * @return boolean risultato dalla verifica
     */
    public static boolean nomeAndCognome(String string) {
        return Pattern.matches("^[a-zA-Z]{2,30}", string);
    }

    /**
     * verifica attraverso una regexp se la stringa in input(userid) è valida
     * <p>
     *     la regexp verifica se la stringa contiene caratteri alfabetici e se la lunghezza è tra i 2 e i 30 caratteri
     * </p>
     * @param string stringa in input per la verifica
     * @return boolean risultato dalla verifica
     */
    public static boolean userId(String string) {
        return Pattern.matches("^[a-z0-9]{1,12}", string);
    }

    /**
     * verifica attraverso una regexp se la stringa in input(password) è valida
     * <p>
     *     la regexp verifica se la stringa contiene almeno 8 caratteri, se ha almeno una maiuscola,
     *     se ha almeno un carattere speciale e se ha almeno un numero
     * </p>
     * @param string stringa in input per la verifica
     * @return boolean risultato dalla verifica
     */
    public static boolean password(String string) {
        return Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", string);
    }

    /**
     * verifica attraverso una regexp se la stringa in input(email) è valida
     * <p>
     *     la regexp verifica se la stringa contiene una email, quindi se ha un un indirizzo prima della @,
     *     se ha un sotto dominio dopo la @ e se dopo il sottodominio c'è il . dominio
     * </p>
     * @param string stringa in input per la verifica
     * @return boolean risultato dalla verifica
     */
    public static boolean email(String string) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", string);
    }

    /**
     * verifica attraverso una regexp se la stringa in input(indirizzo) è valida
     * <p>
     *     la regexp verifica se la stringa contiene (via|corso|viale|piazza|c.so|v.le|p.za), se contiene il nome e
     *     se contiene il numero civico
     * </p>
     * @param string stringa in input per la verifica
     * @return boolean risultato dalla verifica
     */
    public static boolean indirizzo(String string) {
        return Pattern.matches("^(via|corso|viale|piazza|c.so|v.le|p.za) [a-zA-Z ]{1,241}?,? \\d{1,4}",
                string.toLowerCase());
    }

    /**
     * verifica attraverso una regexp se la stringa in input(cap) è valida
     * <p>
     *     la regexp verifica se la stringa contiene 5 caratteri numerici
     * </p>
     * @param string stringa in input per la verifica
     * @return boolean risultato dalla verifica
     */
    public static boolean cap(String string) {
        return Pattern.matches("^[0-9]{5}", string);
    }

    /**
     * metodo generalizzato per i TextField per la rappresentazione grafica dei validatori
     * @param isValid condizione di verifica, validatore
     * @param textField textfield da gestire
     * @param cross l'immagine di errore da gestire
     * @param checkmark l'immagine di successo da gestire
     * @return boolean risultato dalla verifica
     */
    public static boolean textField(boolean isValid, TextField textField, ImageView cross, ImageView checkmark) {
        if (isValid) {
            cross.setVisible(false);
            checkmark.setVisible(true);
            StyleUI.removeRed(textField);
            StyleUI.setGreen(textField);
            return true;
        } else {
            checkmark.setVisible(false);
            cross.setVisible(true);
            StyleUI.removeGreen(textField);
            StyleUI.setRed(textField);
            return false;
        }
    }

    /**
     * metodo generale per la validazione delle ChoiceBox e la rappresentazione grafica della validazione
     * @param choiceBox choiceBox da gestire
     * @param cross l'immagine di errore da gestire
     * @param checkmark l'immagine di successo da gestire
     * @return boolean risultato dalla verifica
     */
    public static boolean choiseBox(ChoiceBox choiceBox, ImageView cross, ImageView checkmark) {
        if (!String.valueOf(choiceBox.getSelectionModel().getSelectedItem()).equals("null")) {
            cross.setVisible(false);
            checkmark.setVisible(true);
            StyleUI.removeRed(choiceBox);
            StyleUI.setGreen(choiceBox);
            return true;
        } else {
            checkmark.setVisible(false);
            cross.setVisible(true);
            StyleUI.removeGreen(choiceBox);
            StyleUI.setRed(choiceBox);
            return false;
        }
    }
}
