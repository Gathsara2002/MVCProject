package lk.ijse.restaurant.model;

import javafx.scene.control.Tab;
import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.Employee;
import lk.ijse.restaurant.to.Table;


import java.sql.*;
import java.util.ArrayList;

public class TableModel {

    public static ArrayList<Table> loadTable() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM TableDetail");

        ArrayList<Table> tableData = new ArrayList<>();

        while (resultSet.next()) {
            tableData.add(
                    new Table(
                            resultSet.getString(1),
                            resultSet.getInt(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );
        }

        return tableData;
    }

    public static int getTableCount() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT tid FROM tabledetail");

        ArrayList<String > list=new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list.size();
    }

    public static boolean addTable(Table table) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Tabledetail VALUES (?,?,?,?,?,?,?)");

        statement.setObject(1,table.getTid());
        statement.setObject(2,table.getCapacity());
        statement.setObject(3,table.getDescription());
        statement.setObject(4,table.getBooking_date());
        statement.setObject(5,table.getBooking_time());
        statement.setObject(6,table.getAvailability());
        statement.setObject(7,table.getCusId());
        int result = statement.executeUpdate();


        if (result>0){
            return true;
        }
        return  false;
    }

    public static ArrayList<String> loadTableIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT tid FROM TableDetail");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> arrayList=new ArrayList<>();

        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static Table searchTable(String tid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM TableDetail WHERE tid=?");

        statement.setObject(1,tid);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return  new Table(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }

        return null;
    }

    public static boolean deleteTable(Table table) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM TableDetail WHERE tid= ? ");

        statement.setObject(1,table.getTid());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public static boolean updateTable(Table table) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Tabledetail SET capacity=?,description=?,booking_date=?,booking_time=?,availability=?,cid=? WHERE tid=?");

        statement.setObject(1,table.getCapacity());
        statement.setObject(2,table.getDescription());
        statement.setObject(3,table.getBooking_date());
        statement.setObject(4,table.getBooking_time());
        statement.setObject(5,table.getAvailability());
        statement.setObject(6,table.getCusId());
        statement.setObject(7,table.getTid());

        int result = statement.executeUpdate();

        return result > 0;

    }
}
