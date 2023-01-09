package util;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Collections;

public class StyleUI {

    public static void setRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    public static void removeRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }
    public static void setGreen(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("ok")) {
            styleClass.add("ok");
        }
    }
    public static void removeGreen(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("ok"));
    }


    public static void setRed(ChoiceBox tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    public static void removeRed(ChoiceBox tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }
    public static void setGreen(ChoiceBox tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("ok")) {
            styleClass.add("ok");
        }
    }
    public static void removeGreen(ChoiceBox tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("ok"));
    }

    public static void setRed(DatePicker tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }
    public static void removeRed(DatePicker tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }
    public static void setGreen(DatePicker tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("ok")) {
            styleClass.add("ok");
        }
    }
    public static void removeGreen(DatePicker tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("ok"));
    }

    public static void setGreen(Label tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("ok")) {
            styleClass.add("ok");
        }
    }
}
