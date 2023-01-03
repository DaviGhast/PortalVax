package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));
    }

    public void OperatoreAction(ActionEvent actionEvent) throws IOException {
        MainUIController.setRoot("operatore_home.fxml");
    }

    public void CittadinoAction(ActionEvent actionEvent) {
    }
}
