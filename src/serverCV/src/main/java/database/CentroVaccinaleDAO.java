package database;

import model.CentroVaccinale;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import static database.DBAccess.getConnect;


public class CentroVaccinaleDAO{

    static Connection conn = getConnect();

    public static ArrayList<CentroVaccinale> getAll() {
        ArrayList<CentroVaccinale> result = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM centro_vaccinale");

            while(rs.next()) {
                result.add(new CentroVaccinale(
                        rs.getShort("id"),
                        rs.getString("nome_centro_vaccinale"),
                        rs.getString("indirizzo"),
                        rs.getString("comune"),
                        rs.getString("sigla_provincia"),
                        rs.getString("tipologia"),
                        rs.getInt("cap")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public static ArrayList<CentroVaccinale> getByName(String nomeCentroVaccinale) {
        ArrayList<CentroVaccinale> result = new ArrayList<>();
        String sql = "SELECT * FROM centro_vaccinale WHERE nome_centro_vaccinale = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nomeCentroVaccinale);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                result.add(new CentroVaccinale(
                        rs.getShort("id"),
                        rs.getString("nome_centro_vaccinale"),
                        rs.getString("indirizzo"),
                        rs.getString("comune"),
                        rs.getString("sigla_provincia"),
                        rs.getString("tipologia"),
                        rs.getInt("cap")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public static boolean insert(CentroVaccinale centroVaccinale) {
        String sql = "INSERT INTO centro_vaccinale(id,nome_centro_vaccinale,indirizzo,comune,sigla_provincia," +
                "cap,tipologia) VALUES(?,?,?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,centroVaccinale.getId());
            preparedStatement.setString(2,centroVaccinale.getNomeCentroVaccinale());
            preparedStatement.setString(3,centroVaccinale.getIndirizzo());
            preparedStatement.setString(4,centroVaccinale.getComune());
            preparedStatement.setString(5,centroVaccinale.getSiglaProvincia());
            preparedStatement.setInt(6,centroVaccinale.getCap());
            preparedStatement.setString(7,centroVaccinale.getTipologia());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static boolean update(CentroVaccinale centroVaccinale) {
        String sql = "UPDATE centro_vaccinale SET nome_centro_vaccinale = ?,indirizzo = ?,comune = ?," +
                "sigla_provincia = ?,cap = ?,tipologia = ? WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,centroVaccinale.getNomeCentroVaccinale());
            preparedStatement.setString(2,centroVaccinale.getIndirizzo());
            preparedStatement.setString(3,centroVaccinale.getComune());
            preparedStatement.setString(4,centroVaccinale.getSiglaProvincia());
            preparedStatement.setInt(5,centroVaccinale.getCap());
            preparedStatement.setString(6,centroVaccinale.getTipologia());
            preparedStatement.setInt(7,centroVaccinale.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static boolean delete(CentroVaccinale centroVaccinale) {
        String sql = "DELETE FROM centro_vaccinale WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,centroVaccinale.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static int nextID(){
        ArrayList<CentroVaccinale> arrayList = getAll();
        if (arrayList.size() > 0){
            CentroVaccinale centroVaccinale = arrayList.get(arrayList.size()-1);
            return centroVaccinale.getId()+1;
        } else {
            return 1;
        }
    }

}
