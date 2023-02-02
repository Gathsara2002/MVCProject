package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

public class AdDeleteStaffFormController implements Initializable {
    public AnchorPane adDeleteStaffAP;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtNic;
    public JFXTextField txtSalary;
    public JFXTextField txtDesignation;
    public JFXTextField txtEmail;
    public JFXTextField txtAge;
    public JFXTextField txtUsername;
    public JFXTextField txtAddress;
    public JFXPasswordField txtPassword;
    public JFXRadioButton rbtnMale;
    public ToggleGroup gender;
    public JFXRadioButton rbtnFemale;
    public JFXComboBox cmbEid;
    public JFXTextField txtGender;

    public void addEmployeeOnAction(ActionEvent actionEvent) {
        String eid= String.valueOf(cmbEid.getValue());
        Employee employee=new Employee(eid);

        try {
            boolean isDeleted= EmployeeModel.deleteEmployee(employee);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted !").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Employee not deleted !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(adDeleteStaffAP,"AdStaffForm.fxml");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setEmployeeIds();

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


}
