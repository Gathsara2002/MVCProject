package lk.ijse.restaurant.model;

import lk.ijse.restaurant.db.DBConnection;
import lk.ijse.restaurant.to.Employee;
import lk.ijse.restaurant.to.User;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeModel {

    public static ArrayList<Employee> loadEmployee() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM Employee");

        ArrayList<Employee> empData=new ArrayList<>();
        while (resultSet.next()){
            empData.add(
                    new Employee(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getDouble(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            resultSet.getInt(10),
                            resultSet.getString(11),
                            resultSet.getString(12)
                    )
            );
        }
        return empData;
    }

    public static Employee loadAdminProfile() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee WHERE eid='E001'");

        if (resultSet.next()){
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getInt(10),
                    resultSet.getString(11),
                    resultSet.getString(12)
            );
        }

        return null;
    }
    public static Employee loadCashierProfile() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee WHERE eid='E002'");

        if (resultSet.next()){
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getInt(10),
                    resultSet.getString(11),
                    resultSet.getString(12)
            );
        }

        return null;
    }

    public static boolean addEmployee(Employee emp) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

        statement.setObject(1,emp.getEid());
        statement.setObject(2,emp.getName());
        statement.setObject(3,emp.getAddress());
        statement.setInt(4,emp.getContact());
        statement.setObject(5,emp.getNic());
        statement.setObject(6,emp.getSalary());
        statement.setObject(7,emp.getEmail());
        statement.setObject(8,emp.getPassWord());
        statement.setObject(9,emp.getDesignation());
        statement.setObject(10,emp.getAge());
        statement.setObject(11,emp.getGender());
        statement.setObject(12,emp.getUserName());

        int result = statement.executeUpdate();


        if (result>0){
            return true;
        }
        return  false;
    }

    public static ArrayList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT eid FROM Employee");
        ResultSet resultSet = statement.executeQuery();

        ArrayList<String> arrayList=new ArrayList<>();

        while (resultSet.next()){
            arrayList.add(resultSet.getString(1));
        }
        return arrayList;
    }

    public static Employee searchEmployee(String eid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employee WHERE eid=?");

        statement.setObject(1,eid);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return  new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getInt(10),
                    resultSet.getString(11),
                    resultSet.getString(12)
            );
        }

        return null;
    }

    public static boolean deleteEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Employee WHERE eid= ? ");

        statement.setObject(1,employee.getEid());

        int result = statement.executeUpdate();

        return result > 0;
    }

    public static boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Employee SET name=?,address=?,contact=?,nic=?,salary=?,email=?,password=?,designation=?,age=?,gender=?,userName=? WHERE eid=?");

        statement.setObject(1,employee.getName());
        statement.setObject(2,employee.getAddress());
        statement.setInt(3,employee.getContact());
        statement.setObject(4,employee.getNic());
        statement.setObject(5,employee.getSalary());
        statement.setObject(6,employee.getEmail());
        statement.setObject(7,employee.getPassWord());
        statement.setObject(8,employee.getDesignation());
        statement.setObject(9,employee.getAge());
        statement.setObject(10,employee.getGender());
        statement.setObject(11,employee.getUserName());
        statement.setObject(12,employee.getEid());


        int result = statement.executeUpdate();

        return result > 0;

    }

}
