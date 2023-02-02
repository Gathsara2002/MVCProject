package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;


import java.io.IOException;
import java.sql.SQLException;

public class AdAddUserFormController {

    public AnchorPane addUserAP;


    public JFXTextField txtUsername;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtEmail;
    public JFXTextField txtRole;
    public JFXPasswordField pwPassword;
    public JFXTextField txtTele;
    public FontAwesomeIconView btnShowPassword;
    public JFXTextField txtShowPassword;

    public void addUserOnAction(ActionEvent actionEvent) {
        String userName = txtUsername.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtTele.getText());
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String password = pwPassword.getText();
        String role = txtRole.getText();

        User user = new User(userName, name, address, contact, nic, email, password, role);

        try {
            boolean isAdded = UserModel.addUser(user);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(addUserAP, "AdUserForm.Fxml");
    }

    public void showPasswordOnAction(MouseEvent mouseEvent) {
        txtShowPassword.setText(pwPassword.getText());
        txtShowPassword.setVisible(true);
        pwPassword.setVisible(false);
    }

    public void onUnamePress(KeyEvent keyEvent) {
    }

    public void onNamePress(KeyEvent keyEvent) {
    }

    public void onAddressPress(KeyEvent keyEvent) {
    }

    public void oncontactPress(KeyEvent keyEvent) {
    }

    public void onNicPress(KeyEvent keyEvent) {
    }

    public void onEmailPress(KeyEvent keyEvent) {
    }

    public void obRolePress(KeyEvent keyEvent) {
    }

    public void onPxPress(KeyEvent keyEvent) {
    }
}
