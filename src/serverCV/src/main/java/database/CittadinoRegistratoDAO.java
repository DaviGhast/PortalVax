package database;

import model.CentroVaccinale;
import model.Cittadino;
import model.CittadinoRegistrato;

import java.sql.*;
import java.util.ArrayList;

import static database.DBAccess.getConnect;

public class CittadinoRegistratoDAO {
    static Connection conn = getConnect();

    /**
     * Il Metodo <code>getAll</code> permette di ottenere tutte le informazioni del cittadino registratto
     * @return Oggetto result valorizzato
     */
    public static ArrayList<CittadinoRegistrato> getAll() {
        ArrayList<CittadinoRegistrato> result = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cittadino_registrato");

            while(rs.next()) {
                result.add(new CittadinoRegistrato(
                        rs.getString("email"),
                        rs.getString("userid"),
                        rs.getString("password"),
                        rs.getString("codice_fiscale")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }
    /**
     * Il Metodo <code>getByCodiceFiscale</code> permette di ottenere tutte le informazioni di un determinato cittadino registrato in base al codice fiscale
     * @param CodiceFiscale stringa contenente il codice fiscale del cittadino registrato ricercato
     * @return Oggetto result valorizzato
     */
    public static CittadinoRegistrato getByCodiceFiscale(String CodiceFiscale) {
        CittadinoRegistrato result = new CittadinoRegistrato();
        String sql = "SELECT * FROM cittadino_registrato WHERE codice_fiscale = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, CodiceFiscale);
            ResultSet rs = preparedStatement.executeQuery();
            result = new CittadinoRegistrato(
                    rs.getString("userid"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("codice_fiscale")
            );
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    /**
     * Il Metodo <code>getByUserId</code> permette di ottenere tutte le informazioni di un determinato cittadino registrato in base all'id
     * @param userid stringa contenente l'id del cittadino registrato ricercato
     * @return Oggetto result valorizzato
     */
    public static CittadinoRegistrato getByUserId(String userid) {
        CittadinoRegistrato result = new CittadinoRegistrato();
        String sql = "SELECT * FROM cittadino_registrato WHERE userid = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userid.toLowerCase());
            ResultSet rs = preparedStatement.executeQuery();
            result = new CittadinoRegistrato(
                    rs.getString("userid"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("codice_fiscale")
            );
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }
    /**
     * Il Metodo <code>getByEmail</code> permette di ottenere tutte le informazioni di un determinato cittadino registrato in base all'email
     * @param email stringa contenente la mail del cittadino registrato ricercato
     * @return Oggetto result valorizzato
     */
    public static CittadinoRegistrato getByEmail(String email) {
        CittadinoRegistrato result = new CittadinoRegistrato();
        String sql = "SELECT * FROM cittadino_registrato WHERE email = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email.toLowerCase());
            ResultSet rs = preparedStatement.executeQuery();
            result = new CittadinoRegistrato(
                    rs.getString("userid"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("codice_fiscale")
            );
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    /**
     * Il Metodo <code>insert</code> permette di inserire tutte le informazioni di un cittadino registrato
     * @param cittadinoregistrato oggetto contenente tutte le informazioni del cittadino registrato da inserire
     * @return Oggetto result valorizzato
     */
    public static boolean insert(CittadinoRegistrato cittadinoregistrato) {
        String sql = "INSERT INTO cittadino_registrato(userid,email,password,codice_fiscale)" +
                " VALUES(?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cittadinoregistrato.getUserId());
            preparedStatement.setString(2,cittadinoregistrato.getEmail());
            preparedStatement.setString(3, cittadinoregistrato.getPassword());
            preparedStatement.setString(4, cittadinoregistrato.getCodiceFiscale());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    /**
     * Il Metodo <code>update</code> permette di aggiornare tutte le informazioni di un cittadino registrato
     * @param cittadinoregistrato oggetto contenente tutte le informazioni del cittadino registrato da aggiornare
     * @return Oggetto result valorizzato
     */
    public static boolean update(CittadinoRegistrato cittadinoregistrato) {
        String sql = "UPDATE cittadino_registrato SET email = ?,password = ?,codice_fiscale = ? WHERE userid = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,cittadinoregistrato.getEmail());
            preparedStatement.setString(2,cittadinoregistrato.getPassword());
            preparedStatement.setString(3,cittadinoregistrato.getCodiceFiscale());
            preparedStatement.setString(4,cittadinoregistrato.getUserId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }
    /**
     * Il Metodo <code>insert</code> permette di cancellare tutte le informazioni di un cittadino registrato
     * @param cittadinoregistrato oggetto contenente tutte le informazioni cittadino registrato da cancellare
     * @return Oggetto result valorizzato
     */
    public static boolean delete(CittadinoRegistrato cittadinoregistrato) {
        String sql = "DELETE FROM cittadino_registrato WHERE userid = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,cittadinoregistrato.getUserId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }
}

