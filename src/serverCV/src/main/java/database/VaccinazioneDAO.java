package database;

import model.CentroVaccinale;
import model.Vaccinazione;

import java.sql.*;
import java.util.ArrayList;

import static database.DBAccess.getConnect;

public class VaccinazioneDAO{

    static Connection conn = getConnect();

    public static ArrayList<Vaccinazione> getAll() {
        ArrayList<Vaccinazione> result = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM vaccinazione");

            while(rs.next()) {
                result.add(new Vaccinazione(
                        rs.getShort("id"),
                        rs.getString("vaccino_somministrato"),
                        rs.getString("data_vaccinazione"),
                        rs.getShort("id_centro_vaccinale"),
                        rs.getString("codice_fiscale")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Vaccinazione> getByIdCentro(short idCentroVaccinale) {
        ArrayList<Vaccinazione> result = new ArrayList<>();
        String sql = "SELECT * FROM vaccinazione WHERE id_centro_vaccinale = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setShort(1, idCentroVaccinale);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                result.add(new Vaccinazione(
                        rs.getShort("id"),
                        rs.getString("vaccino_somministrato"),
                        rs.getString("data_vaccinazione"),
                        rs.getShort("id_centro_vaccinale"),
                        rs.getString("codice_fiscale")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Vaccinazione> getByCodiceFiscale(String codiceFiscale) {
        ArrayList<Vaccinazione> result = new ArrayList<>();
        String sql = "SELECT * FROM vaccinazione WHERE codice_fiscale = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, codiceFiscale);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                result.add(new Vaccinazione(
                        rs.getShort("id"),
                        rs.getString("vaccino_somministrato"),
                        rs.getString("data_vaccinazione"),
                        rs.getShort("id_centro_vaccinale"),
                        rs.getString("codice_fiscale")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    public static boolean insert(Vaccinazione vaccinazione) {
        String sql = "INSERT INTO vaccinazione(id,vaccino_somministrato,data_vaccinazione,id_centro_vaccinale,codice_fiscale)" +
                " VALUES(?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setShort(1,vaccinazione.getId());
            preparedStatement.setString(2,vaccinazione.getVaccinoSomministrato());
            preparedStatement.setString(3,vaccinazione.getDataVaccinazione());
            preparedStatement.setShort(4,vaccinazione.getIdCentroVaccinale());
            preparedStatement.setString(5,vaccinazione.getCodiceFiscale());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static boolean update(Vaccinazione vaccinazione) {
        String sql = "UPDATE vaccinazione SET vsccino_somministarto = ?,data_vaccinazione = ?,id_centro_vaccinale = ?," +
                "codice_fiscale = ? WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,vaccinazione.getVaccinoSomministrato());
            preparedStatement.setString(2,vaccinazione.getDataVaccinazione());
            preparedStatement.setShort(3,vaccinazione.getIdCentroVaccinale());
            preparedStatement.setString(4,vaccinazione.getCodiceFiscale());
            preparedStatement.setShort(5,vaccinazione.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static boolean delete(Vaccinazione vaccinazione) {
        String sql = "DELETE FROM vaccinazione WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,vaccinazione.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static int nextID(){
        ArrayList<Vaccinazione> arrayList = getAll();
        if (arrayList.size() > 0){
            Vaccinazione vaccinazione = arrayList.get(arrayList.size()-1);
            return vaccinazione.getId()+1;
        } else {
            return 1;
        }
    }

}
