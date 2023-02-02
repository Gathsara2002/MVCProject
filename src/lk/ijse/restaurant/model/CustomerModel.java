package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.Customer;
import lk.ijse.restaurant.to.Stock;
import lk.ijse.restaurant.to.User;

import java.sql.*;
import java.util.ArrayList;

public class CustomerModel {
    public static ArrayList<Customer> loadCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM Customer");

        ArrayList<Customer> cusData=new ArrayList<>();
        while (resultSet.next()){
             cusData.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return cusData;
    }

    public static int getCustomerCount() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT cid FROM Customer");

        ArrayList<String > list=new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list.size();
    }
    public static boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?)");

        statement.setObject(1,customer.getCusId());
        statement.setObject(2,customer.getName());
        statement.setObject(3,customer.getAddress());
        statement.setInt(4,customer.getContact());
        statement.setObject(5,customer.getEmail());
        statement.setObject(6,customer.getEid());

        int result = statement.executeUpdate();


        if (result>0){
            return true;
        }
        return  false;
    }

    public static boolean deleteCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Customer WHERE cid= ? ");

        statement.setObject(1,customer.getEid());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public static ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT cid FROM Customer");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> arrayList=new ArrayList<>();

        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static Customer searchCustomer(String cid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customer WHERE cid=?");

        statement.setObject(1,cid);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return  new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }

        return null;
    }

    public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Customer SET name=?,address=?,contact=?,email=?,eid=? WHERE cid=?");

        statement.setObject(1,customer.getName());
        statement.setObject(2,customer.getAddress());
        statement.setObject(3,customer.getContact());
        statement.setObject(4,customer.getEmail());
        statement.setObject(5,customer.getEid());
        statement.setObject(6,customer.getCusId());


        int result = statement.executeUpdate();

        return result > 0;

    }

}
