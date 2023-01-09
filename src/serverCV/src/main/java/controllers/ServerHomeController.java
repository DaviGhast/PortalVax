package controllers;

import database.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.RMIServer;

import java.io.IOException;
import java.net.URL;
import java.rmi.NoSuchObjectException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ServerHomeController implements Initializable {

    public ImageView image, cross_db, checkmark_db;
    public Button button_db, button_stop, button_start, button_close;


    public void loginDB(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainServerUIController.class.getClassLoader().getResource("fxml/server_db_access.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.DECORATED);
        stage.getIcons().add(new Image("images/logo_portalvax.png"));
        stage.setScene(scene);
        stage.setTitle("PortalVax Server - DB Login");
        stage.setResizable(false);
        stage.showAndWait();

        if(DBAccess.getValid()){
            cross_db.setVisible(false);
            checkmark_db.setVisible(true);
            button_db.setDisable(true);
            button_start.setDisable(false);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("images/banner.png"));

        cross_db.setImage(new Image("images/cross.png"));
        checkmark_db.setImage(new Image("images/check_mark.png"));
        cross_db.setVisible(true);
        checkmark_db.setVisible(false);

    }

    public void stopServer(ActionEvent actionEvent) throws NoSuchObjectException {
        RMIServer.stop();
        button_stop.setDisable(true);
        button_start.setDisable(false);
    }

    public void startServer(ActionEvent actionEvent) {
        RMIServer.start();
        RMIServer.checkEnum();
        button_start.setDisable(true);
        button_stop.setDisable(false);
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}
