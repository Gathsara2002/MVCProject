package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.Supplier;
import lk.ijse.restaurant.to.User;

import java.sql.*;
import java.util.ArrayList;

public class SupplierModel {

    public static ArrayList<Supplier> loadSuppliers() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Supplier");

        ArrayList<Supplier> supplierData = new ArrayList<>();

        while (resultSet.next()) {
            supplierData.add(
                    new Supplier(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getInt(6),
                            resultSet.getDouble(7)
                    )
            );
        }

        return  supplierData;
    }
    public static boolean addSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Supplier VALUES (?,?,?,?,?,?,?)");

        statement.setObject(1,supplier.getSupId());
        statement.setObject(2,supplier.getName());
        statement.setObject(3,supplier.getContact());
        statement.setObject(4,supplier.getEmail());
        statement.setObject(5,supplier.getCompany());
        statement.setObject(6,supplier.getNoOfItems());
        statement.setObject(7,supplier.getPayments());

        int result = statement.executeUpdate();

        if (result>0){
            return true;
        }
        return  false;
    }

    public static boolean deleteSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Supplier WHERE supId= ? ");

        statement.setObject(1,supplier.getSupId());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public static ArrayList<String> loadSupplierIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT supId FROM Supplier");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> arrayList=new ArrayList<>();

        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static Supplier searchSupplier(String supId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Supplier WHERE supId=?");

        statement.setObject(1,supId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return  new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getDouble(7)
            );
        }

        return null;
    }

    public static boolean updateSupplier(Supplier supplier) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Supplier SET name=?,contact=?,email=?,company=?,no_of_items=?,payment=? WHERE supId=?");

        statement.setObject(1,supplier.getName());
        statement.setObject(2,supplier.getContact());
        statement.setObject(3,supplier.getEmail());
        statement.setObject(4,supplier.getCompany());
        statement.setObject(5,supplier.getNoOfItems());
        statement.setObject(6,supplier.getPayments());
        statement.setObject(7,supplier.getSupId());


        int result = statement.executeUpdate();

        return result > 0;

    }

}
