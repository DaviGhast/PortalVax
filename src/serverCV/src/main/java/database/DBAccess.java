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
    /**
     * Rappresenta la connessione con il database stabilita con il metodo <code>getConnect</code> della classe {@link DBAccess}
     * @see DBAccess#getConnect()
     */
    private static Connection conn = null;
    private static Boolean isValid = false;

    /**
     * Stringa contenente il valore del host
     */
    private static String host;
    /**
     * Stringa contenente il valore dell'username
     */
    private static String username;
    /**
     * Stringa contenente il valore della password
     */
    private static String password;
    /**
     * Stringa contenente il valore della porta
     */
    private static String porta;
    /**
     * Stringa contenente il valore del nome della base dati
     */
    private static String nomedatabase;

    /**
     * Il metodo <code>setConn</code> stabilisce la connessione alla base dati
     * @param conn il parametro di connessione
     */
    public static void setConn(Connection conn) {
        DBAccess.conn = conn;
    }

    /**
     * Il metodo <code>getValid</code> verifica la validità
     * @return il valore boolean
     */
    public static Boolean getValid() {
        return isValid;
    }

    /**
     * Il metodo <code>setHost</code> stabilisce il host
     * @param host stringa contenente il valore del host
     */
    public static void setHost(String host) {
        DBAccess.host = host;
    }

    /**
     * Il metodo <code>setUserName</code> imposta il valore dell'username
     * @param username stringa contenente il valore dell'username
     */
    public static void setUsername(String username) {
        DBAccess.username = username;
    }
    /**
     * Il metodo <code>setPassword</code> imposta il valore della password
     * @param password stringa contenente il valore della password
     */
    public static void setPassword(String password) {
        DBAccess.password = password;
    }
    /**
     * Il metodo <code>setPorta</code> imposta il valore della porta
     * @param porta stringa contenente il valore della porta
     */
    public static void setPorta(String porta) {
        DBAccess.porta = porta;
    }
    /**
     * Il metodo <code>setNomedatabase</code> imposta il valore del nome della base dati
     * @param nomedatabase stringa contenente il valore del nome della base dato
     */
    public static void setNomedatabase(String nomedatabase) {
        DBAccess.nomedatabase = nomedatabase;
    }

    /**
     * Il metodo <code>DBAccess</code> stabilisce la connessione con la base di dati
     */
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

    /**
     * Il metodo <code>getConnect</code> verifica la connessione
     * @return risposta con il valore del parametro di connessione
     */
    public static Connection getConnect() {
        if (conn == null) {
            new DBAccess();
        }

        return conn;
    }
}
