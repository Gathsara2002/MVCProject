package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.User;

import java.sql.*;
import java.util.ArrayList;

public class UserModel {

    public static ArrayList<User> loadUser() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM User");

        ArrayList<User> userData=new ArrayList<>();

        while (resultSet.next()){
            userData.add(
                   new User(
                           resultSet.getString(1),
                           resultSet.getString(2),
                           resultSet.getString(3),
                           resultSet.getInt(4),
                           resultSet.getString(5),
                           resultSet.getString(6),
                           resultSet.getString(7),
                           resultSet.getString(8)
                   )
            );
        }

        return userData;
    }



    public static boolean addUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,?,?,?)");

            statement.setObject(1,user.getUserName());
            statement.setObject(2,user.getName());
            statement.setObject(3,user.getAddress());
            statement.setInt(4,user.getContact());
            statement.setObject(5,user.getNic());
            statement.setObject(6,user.getEmail());
            statement.setObject(7,user.getPassWord());
            statement.setObject(8,user.getRole());

        int result = statement.executeUpdate();


        if (result>0){
            return true;
        }
        return  false;
    }

    public static boolean deleteUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM User WHERE userName= ? ");

        statement.setObject(1,user.getUserName());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public static ArrayList<String> loadUsername() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT userName FROM User");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> arrayList=new ArrayList<>();

        while (resultSet.next()){
           arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static User searchUser(String userName) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE userName=?");

        statement.setObject(1,userName);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
                  return  new User(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)
            );
        }

        return null;
    }

    public static boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE User SET name=?,address=?,contact=?,nic=?,email=?,password=?,role=? WHERE userName=?");

        statement.setObject(1,user.getName());
        statement.setObject(2,user.getAddress());
        statement.setObject(3,user.getContact());
        statement.setObject(4,user.getNic());
        statement.setObject(5,user.getEmail());
        statement.setObject(6,user.getPassWord());
        statement.setObject(7,user.getRole());
        statement.setObject(8,user.getUserName());


        int result = statement.executeUpdate();

        return result > 0;

    }

    public static int getUerCount() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT userName FROM User");

        ArrayList<String > list=new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list.size();
    }

}
