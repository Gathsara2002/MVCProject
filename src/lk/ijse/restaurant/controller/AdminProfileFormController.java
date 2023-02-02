package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.EmployeeModel;
import lk.ijse.restaurant.to.Employee;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminProfileFormController implements Initializable {
    public AnchorPane profileAP;
    public Label lblUsername;
    public Label lblAddress;
    public Label lblContact;
    public Label lblNic;
    public Label lblGender;
    public Label lblRole;
    public Label lblEmail;
    public Label lblName;
    public PasswordField pwPassword;
    public JFXCheckBox cbShowPassword;
    public Label lblShowPassword;

    public void backOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Employee emp= EmployeeModel.loadAdminProfile();
            lblUsername.setText(" \t"+emp.getUserName());
            lblName.setText(" \t"+emp.getName());
            lblAddress.setText(" \t"+emp.getAddress());
            lblContact.setText(" \t"+String.valueOf(emp.getContact()));
            lblNic.setText(" \t"+emp.getNic());
            lblGender.setText(" \t"+emp.getGender());
            lblRole.setText(" \t"+emp.getDesignation());
            lblEmail.setText(" \t"+emp.getEmail());
            pwPassword.setText(" \t"+emp.getPassWord());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void showPasswordOnAction(ActionEvent actionEvent) {

        if (cbShowPassword.isSelected()){
            lblShowPassword.setText(pwPassword.getText());
            lblShowPassword.setVisible(true);
            pwPassword.setVisible(false);
            return;
        }
        pwPassword.setText(lblShowPassword.getText());
        pwPassword.setVisible(true);
        lblShowPassword.setVisible(false);
    }
}
