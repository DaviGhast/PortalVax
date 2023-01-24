package util;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Collections;

/**
 * La classe <code>StyleUI</code> fornisce dei metodi per gestire gli stili di alcuni oggetti delle view
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class StyleUI {

    /**
     * permette di settare lo stile error nel textfield
     * @param tf textfield da gestire
     */
    public static void setRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    /**
     * permette di togliere lo stile error nel textfield
     * @param tf textfield da gestire
     */
    public static void removeRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }
    /**
     * permette di settare lo stile ok nel textfield
     * @param tf textfield da gestire
     */
    public static void setGreen(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("ok")) {
            styleClass.add("ok");
        }
    }
    /**
     * permette di togliere lo stile ok nel textfield
     * @param tf textfield da gestire
     */
    public static void removeGreen(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("ok"));
    }

    /**
     * permette di settare lo stile error nel choicebox
     * @param tf choicebox da gestire
     */
    public static void setRed(ChoiceBox tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    /**
     * permette di togliere lo stile error nel choicebox
     * @param tf choicebox da gestire
     */
    public static void removeRed(ChoiceBox tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }
    /**
     * permette di settare lo stile ok nel choicebox
     * @param tf choicebox da gestire
     */
    public static void setGreen(ChoiceBox tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("ok")) {
            styleClass.add("ok");
        }
    }
    /**
     * permette di togliere lo stile ok nel choicebox
     * @param tf choicebox da gestire
     */
    public static void removeGreen(ChoiceBox tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("ok"));
    }

    /**
     * permette di settare lo stile error nel datepicker
     * @param tf datepicker da gestire
     */
    public static void setRed(DatePicker tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    /**
     * permette di togliere lo stile error nel datepicker
     * @param tf datepicker da gestire
     */
    public static void removeRed(DatePicker tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }
    /**
     * permette di settare lo stile ok nel datepicker
     * @param tf datepicker da gestire
     */
    public static void setGreen(DatePicker tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("ok")) {
            styleClass.add("ok");
        }
    }
    /**
     * permette di togliere lo stile error nel datepicker
     * @param tf datepicker da gestire
     */
    public static void removeGreen(DatePicker tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("ok"));
    }

    /**
     * permette di settare lo stile ok nel label
     * @param tf label da gestire
     */
    public static void setGreen(Label tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("ok")) {
            styleClass.add("ok");
        }
    }
}
