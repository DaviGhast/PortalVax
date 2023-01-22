package database;

import model.CentroVaccinale;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import static database.DBAccess.getConnect;

/**
 * E' la classe DAO dei CentriVaccinali
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class CentroVaccinaleDAO{

    static Connection conn = getConnect();

    /**
     * Il Metodo <code>getAll</code> permette di ottenere tutte le informazioni del centro vaccinale
     * @return Oggetto result valorizzato
     */
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

    /**
     * Il Metodo <code>getByName</code> permette di ottenere tutte le informazioni di un determinato centro vaccinale in base al nome
     * @param nomeCentroVaccinale stringa contenente il nome del centro vaccinale ricercato
     * @return Oggetto result valorizzato
     */
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

    /**
     * Il Metodo <code>getByComune</code> permette di ottenere tutte le informazioni di un determinato centro vaccinale in base al comune
     * @param comune stringa contenente il nome del comune del centro vaccinale ricercato
     * @return Oggetto result valorizzato
     */
    public static ArrayList<CentroVaccinale> getByComune(String comune) {
        ArrayList<CentroVaccinale> result = new ArrayList<>();
        String sql = "SELECT * FROM centro_vaccinale WHERE comune = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, comune);
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

    /**
     * Il Metodo <code>insert</code> permette di inserire un nuovo centro vaccinale
     * @param centroVaccinale oggetto contenente tutte le informazini del nuovo centro vaccinale
     * @return Oggetto result valorizzato
     */
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
    /**
     * Il Metodo <code>update</code> permette di aggiornare le informazioni di un centro vaccinale
     * @param centroVaccinale oggetto contenente tutte le informazini aggiornate del centro vaccinale
     * @return Oggetto result valorizzato
     */
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
    /**
     * Il Metodo <code>delete</code> permette di cancellare le informazioni di un centro vaccinale
     * @param centroVaccinale oggetto contenente tutte le informazini del centro vaccinale da cancellare
     * @return Oggetto result valorizzato
     */
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
