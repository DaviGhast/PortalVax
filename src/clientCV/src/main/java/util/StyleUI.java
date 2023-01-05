package util;

import javafx.collections.ObservableList;
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

}
