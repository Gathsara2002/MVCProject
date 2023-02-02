package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.EmployeeModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Employee;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.sql.SQLException;

public class AdAddStaffFormController {
    public AnchorPane addStaffAP;
    public JFXTextField txtEid;
    
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
    public JFXRadioButton rbtnMale;
    public JFXRadioButton rbtnFemale;
    public FontAwesomeIconView btnShowPassword;
    public JFXTextField txtShowPassword;
    public ToggleGroup gender;

    public void addEmployeeOnAction(ActionEvent actionEvent) {
        String eid= txtEid.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        int contact= Integer.parseInt(txtContact.getText());
        String nic=txtNic.getText();
        double salary= Double.parseDouble(txtSalary.getText());
        String email=txtEmail.getText();
        String password= txtPassword.getText();
        String role=txtDesignation.getText();
        int age= Integer.parseInt(txtAge.getText());

        String gender="";
        if (rbtnMale.isSelected()){
            gender="Male";
        } else if (rbtnFemale.isSelected()) {
            gender="Female";
        }
        String userName=txtUsername.getText();

        Employee employee=new Employee(eid,name,address,contact,nic,salary,email,password,role,age,gender,userName);

        try {
            boolean isAdded= EmployeeModel.addEmployee(employee);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(addStaffAP,"AdStaffForm.fxml");
    }

    public void showPassword(MouseEvent mouseEvent) {
       txtShowPassword.setText(txtPassword.getText());
       txtShowPassword.setVisible(true);
       txtPassword.setVisible(false);
    }


}
