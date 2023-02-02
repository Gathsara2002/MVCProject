package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.restaurant.util.Navigation;
import lk.ijse.restaurant.util.Routes;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public AnchorPane loginAP;
    public Label lblWarning;
    public JFXTextField txtShowPassword;
    public JFXCheckBox cbPassword;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        String userName = txtUsername.getText();
        String passWord = txtPassword.getText();
        String adminUname = "Admin";
        String adminPword = "1234";
        String cashierUname = "Cashier";
        String cashierPword = "2345";



        if (userName.equals(adminUname) && passWord.equals(adminPword)) {
            Navigation.navigate(Routes.ADMIN_DASHBOARD, loginAP);

        } else if (userName.equals(cashierUname) && passWord.equals(cashierPword)) {
            Navigation.navigate(Routes.CASHIER_DASHBOARD,loginAP);
        }
        else {
            lblWarning.setText("Invalid Username or Password !");
        }

    }

    public void userNameOnPress(KeyEvent keyEvent) {
        if(keyEvent.getCode()== KeyCode.ENTER){
            txtPassword.requestFocus();
        }
    }

    public void showPasswordOnAction(ActionEvent actionEvent) {
        if (cbPassword.isSelected()){
            txtShowPassword.setDisable(false);
            txtShowPassword.setText(txtPassword.getText());
            txtShowPassword.setVisible(true);
            txtPassword.setVisible(false);
            return;
        }
        txtPassword.setText(txtShowPassword.getText());
        txtShowPassword.setDisable(true);
        txtShowPassword.setVisible(false);
        txtPassword.setVisible(true);
    }
}
