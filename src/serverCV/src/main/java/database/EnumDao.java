package database;

import model.EnumModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import static database.DBAccess.getConnect;
/**
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class EnumDao {

    static Connection conn = getConnect();

    public static String[] getEnumList(String enumName) {
        String[] result = null;
        String SELECT_QUERY = "SELECT * FROM enum WHERE name = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, enumName);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                String list = rs.getString("list");
                if (!list.equals("[]"))
                    result = stringToArray(rs.getString("list"));
            }

        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return result;
    }

    public static boolean insert(EnumModel enumModel) {
        String sql = "INSERT INTO enum(id,name,list) VALUES(?,?,?);";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, enumModel.getEnumID());
            preparedStatement.setString(2, enumModel.getEnumName());
            preparedStatement.setString(3, Arrays.toString(enumModel.getEnumList()));
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }
        if (result == 1){
            return true;
        } else {
            return false;
        }
    }

    public static boolean update(EnumModel enumModel) {
        String sql = "UPDATE enum SET list = ? WHERE name = ?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, Arrays.toString(enumModel.getEnumList()));
            preparedStatement.setString(2, enumModel.getEnumName());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }
        if (result == 1){
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<EnumModel> getAll() {
        ArrayList<EnumModel> result = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from enum");

            while(rs.next()) {
                result.add(new EnumModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        stringToArray(rs.getString("list"))
                ));
            }
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return result;
    }

    public static int nextID(){
        ArrayList<EnumModel> arrayList = getAll();
        if (arrayList.size() > 0){
            EnumModel enumModel = arrayList.get(arrayList.size()-1);
            return enumModel.getEnumID()+1;
        } else {
            return 1;
        }
    }

    public static String[] stringToArray(String string) {
        return string.replace("[","").replace("]","").split(", ");
    }
}
