package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.Bill;
import lk.ijse.restaurant.to.Payment;

import java.sql.*;
import java.util.ArrayList;

public class BillModel {
    public static ArrayList<Bill> loadBill() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM Bill");

        ArrayList<Bill> billData=new ArrayList<>();
        while (resultSet.next()){
            billData.add(
                    new Bill(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)

                    )
            );
        }
        return billData;
    }
    public static boolean addBill(Bill bill) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Bill VALUES (?,?,?,?,?)");

        statement.setObject(1, bill.getBillNo());
        statement.setObject(2,bill.getDate());
        statement.setObject(3,bill.getTime());
        statement.setObject(4,bill.getDesc());
        statement.setObject(5,bill.getPid());

        int result = statement.executeUpdate();


        if (result>0){
            return true;
        }
        return  false;
    }
}
