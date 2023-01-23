package util;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.regex.Pattern;

public class Validator {

    public static boolean codiceFiscale(String string) {
        return Pattern.matches("^[A-Z0-9]{16}", string);
    }

    public static boolean nomeAndCognome(String string) {
        return Pattern.matches("^[a-zA-Z]{2,30}", string);
    }
    public static boolean userId(String string) {
        return Pattern.matches("^[A-Za-z0-9]{1,12}", string);
    }
    public static boolean password(String string) {
        return Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", string);
    }
    public static boolean email(String string) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", string);
    }

    public static boolean indirizzo(String string) {
        return Pattern.matches("^(via|corso|viale|piazza|c.so|v.le|p.za) [a-zA-Z ]{1,241}?,?\\d{1,4}",
                string.toLowerCase());
    }

    public static boolean cap(String string) {
        return Pattern.matches("^[0-9]{5}", string);
    }

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
