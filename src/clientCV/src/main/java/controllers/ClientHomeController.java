package controllers;

import client.RMIClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.rmi.NoSuchObjectException;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class ClientHomeController implements Initializable {

    public ImageView image;
    public Button button_start;
    public TextField tf_nomepc;
    public Text text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));

        text.setVisible(false);
        tf_nomepc.setVisible(false);

    }

    public void start(ActionEvent actionEvent) throws IOException {
        RMIClient.setNameServer(tf_nomepc.getText());
        if (RMIClient.serverConnection()) {
            MainClientUIController.setRoot("home");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainClientUIController.class.getClassLoader().getResource("fxml/error.fxml"));
            DialogPane pane = fxmlLoader.load();

            ErrorController errorController = fxmlLoader.getController();

            Alert dialog = new Alert(Alert.AlertType.NONE);
            dialog.setDialogPane(pane);
            dialog.setTitle("Errore");
            ButtonType chiudi = new ButtonType("Chiudi", ButtonBar.ButtonData.BACK_PREVIOUS);
            dialog.getDialogPane().getButtonTypes().add(chiudi);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent()) {

            }
        }
    }

    public void cliccaSi(ActionEvent actionEvent) {
        tf_nomepc.setText("");
        text.setVisible(false);
        tf_nomepc.setVisible(false);
    }

    public void cliccaNo(ActionEvent actionEvent) {
        tf_nomepc.setText("");
        text.setVisible(true);
        tf_nomepc.setVisible(true);
    }
}
