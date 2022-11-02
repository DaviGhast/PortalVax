package database;

import model.Cittadino;
import model.Vaccinazione;

import java.sql.*;
import java.util.ArrayList;

import static database.DBAccess.getConnect;

public class CittadinoDAO{

    static Connection conn = getConnect();

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
