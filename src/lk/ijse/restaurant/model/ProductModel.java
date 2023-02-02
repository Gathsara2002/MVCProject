package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.Products;


import java.sql.*;
import java.util.ArrayList;

public class ProductModel {
    public static ArrayList<Products> loadProducts() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Fooditem");

        ArrayList<Products> productData = new ArrayList<>();

        while (resultSet.next()) {
            productData.add(
                    new Products(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5)

                    )
            );
        }

        return productData;
    }


    public static boolean addProduct(Products products) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Fooditem VALUES (?,?,?,?,?)");

        statement.setObject(1, products.getFid());
        statement.setObject(2, products.getItemName());
        statement.setObject(3, products.getCategory());
        statement.setInt(4, products.getItemPrice());
        statement.setInt(5, products.getQty());


        int result = statement.executeUpdate();


        if (result > 0) {
            return true;
        }
        return false;
    }

    public static boolean deleteProduct(Products products) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Fooditem WHERE fid= ? ");

        statement.setObject(1, products.getFid());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public static ArrayList<String> loadFids() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT fid FROM Fooditem");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> arrayList = new ArrayList<>();

        while (resultSet.next()) {
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static Products searchProducts(String fid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Fooditem WHERE fid=?");

        statement.setObject(1, fid);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new Products(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5)
            );
        }

        return null;
    }

    public static boolean updateProduct(Products products) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Fooditem SET itemName=?,category=?,itemPrice=?,qty=? WHERE fid=?");

        statement.setObject(1, products.getItemName());
        statement.setObject(2, products.getCategory());
        statement.setObject(3, products.getItemPrice());
        statement.setObject(4, products.getQty());
        statement.setObject(5, products.getFid());


        int result = statement.executeUpdate();

        return result > 0;

    }

}