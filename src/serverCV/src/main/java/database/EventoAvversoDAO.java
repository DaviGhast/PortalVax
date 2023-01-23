package database;

import model.EventoAvverso;

import java.sql.*;
import java.util.ArrayList;

import static database.DBAccess.getConnect;
/**
 * E' la classe DAO dei EventiAvversi
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class EventoAvversoDAO {
    /**
     * Rappresenta la connessione con il database stabilita con il metodo <code>getConnect</code> della classe {@link DBAccess}
     * @see DBAccess#getConnect()
     */
    static Connection conn = getConnect();

    /**
     * Il Metodo <code>getAll</code> permette di ottenere tutte le informazioni dell'evento avverso
     * @return Oggetto result valorizzato
     */
    public static ArrayList<EventoAvverso> getAll() {
        ArrayList<EventoAvverso> result = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM evento_avverso");

            while(rs.next()) {
                result.add(new EventoAvverso(
                        rs.getShort("id"),
                        rs.getString("evento"),
                        rs.getByte("severità"),
                        rs.getString("id_cittadino"),
                        rs.getString("note")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    /**
     * Il Metodo <code>getByIdCittadino</code> permette di ottenere tutte le informazioni degli eventi avversi di un cittadino in base al suo id
     * @param idCittadino stringa contenente l'id del cittadino di cui cui si cerca gli eventi avversi
     * @return Oggetto result valorizzato
     */
    public static ArrayList<EventoAvverso> getByIdCittadino(String idCittadino) {
        ArrayList<EventoAvverso> result = new ArrayList<>();
        String sql = "SELECT * FROM evento_avverso WHERE id_cittadino = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idCittadino);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                result.add(new EventoAvverso(
                        rs.getShort("id"),
                        rs.getString("evento"),
                        rs.getByte("severità"),
                        rs.getString("id_cittadino"),
                        rs.getString("note")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    /**
     * Il Metodo <code>insert</code> permette di inserire un nuovo evento avverso
     * @param eventoAvverso oggetto contenente tutte le informazini dell'evento avverso
     * @return Oggetto result valorizzato
     */
    public static boolean insert(EventoAvverso eventoAvverso) {
        String sql = "INSERT INTO evento_avverso(id,evento,severità,id_cittadino,note)" +
                " VALUES(?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setShort(1,eventoAvverso.getId());
            preparedStatement.setString(2,eventoAvverso.getEvento());
            preparedStatement.setByte(3, eventoAvverso.getSeverita());
            preparedStatement.setString(4, eventoAvverso.getIdCittadino());
            preparedStatement.setString(5, eventoAvverso.getNote());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }
    /**
     * Il Metodo <code>update</code> permette di aggiornare le informazioni di un evento avverso
     * @param eventoavverso oggetto contenente tutte le informazini delL'evento avverso
     * @return Oggetto result valorizzato
     */
    public static boolean update(EventoAvverso eventoavverso) {
        String sql = "UPDATE evento_avverso SET evento = ?,severità = ?,id_cittadino = ?,note = ? WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,eventoavverso.getEvento());
            preparedStatement.setByte(2,eventoavverso.getSeverita());
            preparedStatement.setString(3,eventoavverso.getIdCittadino());
            preparedStatement.setString(4,eventoavverso.getNote());
            preparedStatement.setShort(5,eventoavverso.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    /**
     * Il Metodo <code>delete</code> permette di cancellare un evento avverso
     * @param eventoavverso oggetto contenente tutte le informazini dell'evento avverso da cancellare
     * @return Oggetto result valorizzato
     */
    public static boolean delete(EventoAvverso eventoavverso) {
        String sql = "DELETE FROM evento_avverso WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setShort(1,eventoavverso.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }
    /**
     * Il metodo <code>nextID</code> restituisce il prossimo id utile per l'inserimento di un nuovo oggeto nella tabella della base dati
     * @return restituisce il valore
     */
    public static int nextID(){
        ArrayList<EventoAvverso> arrayList = getAll();
        if (arrayList.size() > 0){
            EventoAvverso eventoavverso = arrayList.get(arrayList.size()-1);
            return eventoavverso.getId()+1;
        } else {
            return 1;
        }
    }
}
