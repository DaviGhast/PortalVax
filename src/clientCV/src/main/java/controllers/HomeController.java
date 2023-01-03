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

public class HomeController implements Initializable {
    public Button button_op;
    public ImageView image;
    public Button button_citt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));
    }

    public void operatoreAction(ActionEvent actionEvent) throws IOException {
        MainClientUIController.setRoot("operatore_home");
    }

    public void cittadinoAction(ActionEvent actionEvent) {
    }
}
