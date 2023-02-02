package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdUpdateUserFormController implements Initializable {
    public AnchorPane updateUserAP;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtNic;
    public JFXTextField txtEmail;
    public JFXTextField txtRole;
    public JFXPasswordField pwPassword;
    public ComboBox cmbUsername;

    public void updateUserOnAction(ActionEvent actionEvent) {
        String username= String.valueOf(cmbUsername.getValue());
        String name=txtName.getText();
        String address=txtAddress.getText();
        int contact= Integer.parseInt(txtContact.getText());
        String nic=txtNic.getText();
        String email=txtEmail.getText();
        String password=pwPassword.getText();
        String role= txtRole.getText();

        User user = new User(username,name,address,contact,nic,email,password,role);
        try {
            boolean isUpdated=UserModel.updateUser(user);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "User Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "User not updated !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void cmbSelectOnAction(ActionEvent actionEvent) {
        String userName = String.valueOf(cmbUsername.getValue());
        try {
            User user = UserModel.searchUser(userName);
            fillUserData(user);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void showPasswordOnAction(MouseEvent mouseEvent) {
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(updateUserAP,"AdUserForm.Fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUsername();

    }

    private void setUsername(){

        try {
            ObservableList<String> list= FXCollections.observableArrayList();
            ArrayList<String> ar= UserModel.loadUsername();

            for (String code:ar) {
                list.add(code);
            }
            cmbUsername.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillUserData(User user) {
        txtName.setText(user.getName());
        txtAddress.setText(user.getAddress());
        txtContact.setText(String.valueOf(user.getContact()));
        txtNic.setText(user.getNic());
        txtEmail.setText(user.getEmail());
        pwPassword.setText(user.getPassWord());
        txtRole.setText(user.getRole());
    }
}
