package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.model.EmployeeModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Employee;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdUpdateStaffFormController implements Initializable {
    public AnchorPane adUpdateStaffAP;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtNic;
    public JFXTextField txtSalary;
    public JFXTextField txtEmail;
    public JFXTextField txtDesignation;
    public JFXTextField txtAge;
    public JFXTextField txtUsername;
    public JFXTextField txtAddress;
    public JFXPasswordField txtPassword;
    public JFXComboBox cmbEid;
    public JFXTextField txtGender;

    public void updateEmployeeOnAction(ActionEvent actionEvent) {
        String eid= String.valueOf(cmbEid.getValue());
        String name=txtName.getText();
        String address =txtAddress.getText();
        int  contact= Integer.parseInt(txtContact.getText());
        String nic=txtNic.getText();
        double salary= Double.parseDouble(txtSalary.getText());
        String email=txtEmail.getText();
        String password=txtPassword.getText();
        String designation=txtDesignation.getText();
        String userName=txtUsername.getText();
        String gender=txtGender.getText();
        int age= Integer.parseInt(txtAge.getText());

        Employee employee=new Employee(eid,name,address, contact,nic, salary,email,password,designation,age,gender,userName);

        try {
            boolean isUpdated= EmployeeModel.updateEmployee(employee);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Employee not updated !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(adUpdateStaffAP,"AdStaffForm.fxml");
    }

    public void showPassword(MouseEvent mouseEvent) {

    }

    public void cmbEidOnAction(ActionEvent actionEvent) {
        String eid = String.valueOf(cmbEid.getValue());
        try {
            Employee employee = EmployeeModel.searchEmployee(eid);
            fillUserData(employee);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setEmployeeIds(){

        try {
            ObservableList<String> list= FXCollections.observableArrayList();
            ArrayList<String> ar= EmployeeModel.loadEmployeeIds();

            for (String code:ar) {
                list.add(code);
            }
            cmbEid.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillUserData(Employee employee) {
        txtName.setText(employee.getName());
        txtAddress.setText(employee.getAddress());
        txtContact.setText(String.valueOf(employee.getContact()));
        txtNic.setText(employee.getNic());
        txtSalary.setText(String.valueOf(employee.getSalary()));
        txtEmail.setText(employee.getEmail());
        txtPassword.setText(employee.getPassWord());
        txtDesignation.setText(employee.getDesignation());
        txtAge.setText(String.valueOf(employee.getAge()));
        txtUsername.setText(employee.getUserName());
        txtGender.setText(employee.getGender());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEmployeeIds();
    }
}
