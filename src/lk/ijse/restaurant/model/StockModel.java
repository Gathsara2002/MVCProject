package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.Stock;
import lk.ijse.restaurant.to.User;

import java.sql.*;
import java.util.ArrayList;

public class StockModel {
    public static ArrayList<Stock> loadStock() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Stock");

        ArrayList<Stock> stockData = new ArrayList<>();

        while (resultSet.next()) {
            stockData.add(
                    new Stock(
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

        return  stockData;
    }
    public static boolean addStock(Stock stock) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Stock VALUES (?,?,?,?,?,?,?)");

        statement.setObject(1,stock.getSid());
        statement.setObject(2,stock.getItemName());
        statement.setObject(3,stock.getCategory());
        statement.setInt(4,stock.getQtyOnHand());
        statement.setInt(5,stock.getUnitPrice());
        statement.setObject(6,stock.getStatus());
        statement.setObject(7,stock.getEid());

        int result = statement.executeUpdate();


        if (result>0){
            return true;
        }
        return  false;
    }
    public static boolean deleteStock(Stock stock) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Stock WHERE sid= ? ");

        statement.setObject(1,stock.getSid());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public static ArrayList<String> loadStockIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT sid FROM Stock");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> arrayList=new ArrayList<>();

        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static Stock searchStock(String sid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Stock WHERE sid=?");

        statement.setObject(1,sid);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return  new Stock(
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

    public static boolean updateStock(Stock stock) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Stock SET item_name=?,catagory=?,qtyOnHand=?,unitPrice=?,status=?,eid=? WHERE sid=?");

        statement.setObject(1,stock.getItemName());
        statement.setObject(2,stock.getCategory());
        statement.setObject(3,stock.getQtyOnHand());
        statement.setObject(4,stock.getUnitPrice());
        statement.setObject(5,stock.getStatus());
        statement.setObject(6,stock.getEid());
        statement.setObject(7,stock.getSid());


        int result = statement.executeUpdate();

        return result > 0;

    }

}
