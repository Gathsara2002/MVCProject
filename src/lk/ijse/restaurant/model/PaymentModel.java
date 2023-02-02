package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.Employee;
import lk.ijse.restaurant.to.Payment;

import java.sql.*;
import java.util.ArrayList;

public class PaymentModel {
    public static ArrayList<Payment> loadPayment() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM Payment");

        ArrayList<Payment> payData=new ArrayList<>();
        while (resultSet.next()){
            payData.add(
                    new Payment(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)

                    )
            );
        }
        return payData;
    }
    public static boolean addPayment(Payment pay) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Payment VALUES (?,?,?,?,?)");

        statement.setObject(1,pay.getPid());
        statement.setObject(2,pay.getDate());
        statement.setObject(3,pay.getTime());
        statement.setObject(4,pay.getType());
        statement.setObject(5,pay.getTotal());

        int result = statement.executeUpdate();


        if (result>0){
            return true;
        }
        return  false;
    }

    public static ArrayList<String> loadPaymentIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT pid FROM Payment");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> arrayList=new ArrayList<>();

        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static Payment searchPayment(String pid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Payment WHERE pid=?");

        statement.setObject(1,pid);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return  new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
        }

        return null;
    }

    public static boolean deletePayment(Payment pay) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Payment WHERE pid= ? ");

        statement.setObject(1,pay.getPid());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public static boolean updatePayment(Payment pay) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Payment SET date=?,time=?,type=?,total=? WHERE pid=?");

        statement.setObject(1,pay.getDate());
        statement.setObject(2,pay.getTime());
        statement.setObject(3,pay.getType());
        statement.setObject(4,pay.getTotal());
        statement.setObject(5,pay.getPid());



        int result = statement.executeUpdate();

        return result > 0;

    }


}
