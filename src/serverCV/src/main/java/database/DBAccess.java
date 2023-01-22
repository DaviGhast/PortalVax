package database;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Properties;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class DBAccess {

    private static Connection conn = null;
    private static Boolean isValid = false;

    private static String host;
    private static String username;
    private static String password;
    private static String porta;
    private static String nomedatabase;

    public static void setConn(Connection conn) {
        DBAccess.conn = conn;
    }

    public static Boolean getValid() {
        return isValid;
    }

    public static void setHost(String host) {
        DBAccess.host = host;
    }

    public static void setUsername(String username) {
        DBAccess.username = username;
    }

    public static void setPassword(String password) {
        DBAccess.password = password;
    }

    public static void setPorta(String porta) {
        DBAccess.porta = porta;
    }

    public static void setNomedatabase(String nomedatabase) {
        DBAccess.nomedatabase = nomedatabase;
    }

    private DBAccess() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://"+host+":"+porta+"/"+nomedatabase;
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            props.setProperty("ssl", "false");
            conn = DriverManager.getConnection(url, props);
            isValid = true;
            System.out.println("SQLite - Connection Established to: "+url);
        } catch (SQLException sqlException) {
            System.err.println("SQLite - Connection Failed : " + sqlException);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQLite");
            alert.setHeaderText("Connessione Fallita");
            alert.setContentText(sqlException.getMessage());
            alert.show();
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("SQLite - Could Not Load Drivers : " + classNotFoundException);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQLite");
            alert.setHeaderText("Impossibile caricare i driver");
            alert.setContentText(classNotFoundException.getMessage());
            alert.show();
        }

    }

    public static Connection getConnect() {
        if (conn == null) {
            new DBAccess();
        }

        return conn;
    }
}
