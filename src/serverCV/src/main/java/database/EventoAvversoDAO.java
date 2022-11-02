package database;

import model.CittadinoRegistrato;
import model.EventoAvverso;

import java.sql.*;
import java.util.ArrayList;

import static database.DBAccess.getConnect;

public class EventoAvversoDAO {
    static Connection conn = getConnect();

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
                        rs.getShort("id_cittadino"),
                        rs.getString("note")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

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
                        rs.getShort("id_cittadino"),
                        rs.getString("note")
                ));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }


    public static boolean insert(EventoAvverso eventoAvverso) {
        String sql = "INSERT INTO evento_avverso(id,evento,severità,id_cittadino,note)" +
                " VALUES(?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setShort(1,eventoAvverso.getId());
            preparedStatement.setString(2,eventoAvverso.getEvento());
            preparedStatement.setByte(3, eventoAvverso.getSeverità());
            preparedStatement.setShort(4, eventoAvverso.getIdCittadino());
            preparedStatement.setString(5, eventoAvverso.getNote());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

    public static boolean update(EventoAvverso eventoavverso) {
        String sql = "UPDATE evento_avverso SET evento = ?,severità = ?,id_cittadino = ?,note = ? WHERE id = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,eventoavverso.getEvento());
            preparedStatement.setByte(2,eventoavverso.getSeverità());
            preparedStatement.setShort(3,eventoavverso.getIdCittadino());
            preparedStatement.setString(4,eventoavverso.getNote());
            preparedStatement.setShort(5,eventoavverso.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result == 1;
    }

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
