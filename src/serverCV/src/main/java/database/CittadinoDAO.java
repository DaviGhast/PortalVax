package database;

import model.Cittadino;
import model.CittadinoRegistrato;
import model.Vaccinazione;

import java.sql.*;
import java.util.ArrayList;

import static database.DBAccess.getConnect;

public class CittadinoDAO{

    static Connection conn = getConnect();

    /**
     * Il Metodo <code>getAll</code> permette di ottenere tutte le informazioni del cittadino
     * @return Oggetto result valorizzato
     */
    public static ArrayList<Cittadino> getAll() {
        ArrayList<Cittadino> result = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cittadino");

            while(rs.next()) {
                result.add(new Cittadino(
                        rs.getString("codice_fiscale"),
                        rs.getString("nome_cittadino"),
                        rs.getString("cognome_cittadino")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    /**
     * Il Metodo <code>getCittadino</code> permette di ottenere tutte le informazioni di un determinato cittadino in base al codice fiscale
     * @param CodiceFiscale stringa contenente il codice fiscale del cittadino ricercato
     * @return Oggetto result valorizzato
     */
    public static Cittadino getCittadino(String CodiceFiscale) {
        Cittadino result = new Cittadino();
        String sql = "SELECT * FROM cittadino WHERE codice_fiscale = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, CodiceFiscale);
            ResultSet rs = preparedStatement.executeQuery();
            result = new Cittadino(
                    rs.getString("codice_fiscale"),
                    rs.getString("nome_cittadino"),
                    rs.getString("cognome_cittadino")
            );
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    /**
     * Il Metodo <code>insert</code> permette di inserire un nuovo cittadino
     * @param cittadino oggetto contenente tutte le informazini del nuovo cittadino
     * @return Oggetto result valorizzato
     */
    public static boolean insert(Cittadino cittadino) {
        String sql = "INSERT INTO cittadino(codice_fiscale,nome_cittadino,cognome_cittadino)" +
                " VALUES(?,?,?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,cittadino.getCodiceFiscale());
            preparedStatement.setString(2,cittadino.getNomeCittadino());
            preparedStatement.setString(3, cittadino.getCognomeCittadino());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    /**
     * Il Metodo <code>update</code> permette di aggiornare le informazioni di un cittadino
     * @param cittadino oggetto contenente tutte le informazini aggiornate del cittadino
     * @return Oggetto result valorizzato
     */
    public static boolean update(Cittadino cittadino) {
        String sql = "UPDATE cittadino SET nome_cittadino = ?,cognome_cittadino = ? WHERE codice_fiscale = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,cittadino.getNomeCittadino());
            preparedStatement.setString(2, cittadino.getCognomeCittadino());
            preparedStatement.setString(3,cittadino.getCodiceFiscale());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }
    /**
     * Il Metodo <code>delete</code> permette di cancellare le informazioni di un cittadino
     * @param cittadino oggetto contenente tutte le informazini del cittadino da cancellare
     * @return Oggetto result valorizzato
     */
    public static boolean delete(Cittadino cittadino) {
        String sql = "DELETE FROM cittadino WHERE codice_fiscale = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,cittadino.getCodiceFiscale());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }
}
