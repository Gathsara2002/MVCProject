package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.Order;
import lk.ijse.restaurant.to.User;

import java.sql.*;
import java.util.ArrayList;

public class OrderModel {

    public static ArrayList<Order> loadOrder() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders");

        ArrayList<Order> orderData = new ArrayList<>();

        while (resultSet.next()) {
            orderData.add(
                    new Order(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );
        }

        return  orderData;
    }

    public static int getOrderCount() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT oid FROM Orders");

        ArrayList<String > list=new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list.size();
    }

    public static boolean addOrder(Order order) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Orders VALUES (?,?,?,?,?,?,?)");

        statement.setObject(1,order.getOid());
        statement.setObject(2,order.getDate());
        statement.setObject(3,order.getTime());
        statement.setInt(4,order.getNoOfItems());
        statement.setObject(5,order.getTotal());
        statement.setObject(6,order.getPid());
        statement.setObject(7,order.getCid());

        int result = statement.executeUpdate();


        if (result>0){
            return true;
        }
        return  false;
    }

    public static boolean deleteOrder(Order order) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Orders WHERE oid= ? ");

        statement.setObject(1,order.getOid());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public static ArrayList<String> loadOrderIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT oid FROM Orders");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> arrayList=new ArrayList<>();

        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static Order searchUser(String oid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Orders WHERE oid=?");

        statement.setObject(1,oid);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return  new Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }

        return null;
    }

    public static boolean updateOrder(Order order) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Orders SET date=?,time=?,no_of_items=?,total=?,pid=?,cid=? WHERE oid=?");

        statement.setObject(1,order.getDate());
        statement.setObject(2,order.getTime());
        statement.setObject(3,order.getNoOfItems());
        statement.setObject(4,order.getTotal());
        statement.setObject(5,order.getPid());
        statement.setObject(6,order.getCid());
        statement.setObject(7,order.getOid());


        int result = statement.executeUpdate();

        return result > 0;

    }

    public static String nextOrderId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT oid FROM Orders BY oid DESC LIMIT 1");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return generateNextOrderId(resultSet.getString(1));
        }
        return generateNextOrderId(resultSet.getString(null));
    }

    private static String generateNextOrderId(String string) {
        if (string != null) {
            String[] split = string.split("DO");
            System.out.println(split[1]);
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "DO" + id;
        }
        return "O001";
    }
}
