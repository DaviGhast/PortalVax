package database;

import model.CentroVaccinale;
import model.Cittadino;
import model.CittadinoRegistrato;

import java.sql.*;
import java.util.ArrayList;

import static database.DBAccess.getConnect;

public class CittadinoRegistratoDAO {
    static Connection conn = getConnect();

    public static ArrayList<CittadinoRegistrato> getAll() {
        ArrayList<CittadinoRegistrato> result = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cittadino_registrato");

            while(rs.next()) {
                result.add(new CittadinoRegistrato(
                        rs.getShort("id"),
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

    public static ArrayList<CittadinoRegistrato> getByCodiceFiscale(String CodiceFiscale) {
        ArrayList<CittadinoRegistrato> result = new ArrayList<>();
        String sql = "SELECT * FROM cittadino_registrato WHERE codice_fiscale = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, CodiceFiscale);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                result.add(new CittadinoRegistrato(
                        rs.getShort("id"),
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


    public static boolean insert(CittadinoRegistrato cittadinoregistrato) {
        String sql = "INSERT INTO cittadino_registrato(id,email,userid,password,codice_fiscale)" +
                " VALUES(?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setShort(1,cittadinoregistrato.getId());
            preparedStatement.setString(2,cittadinoregistrato.getEmail());
            preparedStatement.setString(3, cittadinoregistrato.getUserId());
            preparedStatement.setString(4, cittadinoregistrato.getPassword());
            preparedStatement.setString(5, cittadinoregistrato.getCodiceFiscale());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static boolean update(CittadinoRegistrato cittadinoregistrato) {
        String sql = "UPDATE cittadino_registrato SET email = ?,userid = ?,password = ?,codice_fiscale = ? WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,cittadinoregistrato.getEmail());
            preparedStatement.setString(2,cittadinoregistrato.getUserId());
            preparedStatement.setString(3,cittadinoregistrato.getPassword());
            preparedStatement.setString(4,cittadinoregistrato.getCodiceFiscale());
            preparedStatement.setShort(5,cittadinoregistrato.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static boolean delete(CittadinoRegistrato cittadinoregistrato) {
        String sql = "DELETE FROM cittadino_registrato WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setShort(1,cittadinoregistrato.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static int nextID(){
        ArrayList<CittadinoRegistrato> arrayList = getAll();
        if (arrayList.size() > 0){
            CittadinoRegistrato cittadinoregistrato = arrayList.get(arrayList.size()-1);
            return cittadinoregistrato.getId()+1;
        } else {
            return 1;
        }
    }
}

