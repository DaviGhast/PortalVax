package database;

import java.sql.*;
import java.util.Properties;

public class DBAccess {

    private static Connection conn = null;

    private DBAccess() {
        try {
            Class.forName("org.postgresql.Driver");
            String host = "localhost";
            String url = "jdbc:postgresql://"+host+"/portalvaxdb";
            Properties props = new Properties();
            props.setProperty("user", "fred");
            props.setProperty("password", "secret");
            props.setProperty("ssl", "true");
            Connection conn = DriverManager.getConnection(url, props);

            System.out.println("SQLite - Connection Established to: "+url);
        } catch (SQLException sqlException) {
            System.err.println("SQLite - Connection Failed : " + sqlException);
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("SQLite - Could Not Load Drivers : " + classNotFoundException);
        }

    }

    public static Connection getConnect() {
        if (conn == null) {
            new DBAccess();
        }

        return conn;
    }
}
