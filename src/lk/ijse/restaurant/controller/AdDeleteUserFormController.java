package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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

public class AdDeleteUserFormController implements Initializable {
    public AnchorPane deleteUserAP;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtNic;
    public JFXTextField txtEmail;
    public JFXTextField txtRole;
    public JFXPasswordField pwPassword;
    public ComboBox<String> cmbUsername;
    public AnchorPane updateUserAP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUsername();
    }

    public void deleteUserOnAction(ActionEvent actionEvent) {
        String userName= String.valueOf(cmbUsername.getValue());
        User user=new User(userName);

        try {
            boolean isDeleted= UserModel.deleteUser(user);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted !").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "User not deleted !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(deleteUserAP,"AdUserForm.Fxml");
    }

    public void showPasswordOnAction(MouseEvent mouseEvent) {
    }

    private void setUsername(){

        try {
            ObservableList<String> list=FXCollections.observableArrayList();
            ArrayList<String> ar=UserModel.loadUsername();

            for (String code:ar) {
                list.add(code);
            }
            cmbUsername.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void cmbSelectOnAction(ActionEvent actionEvent) {
        String userName = cmbUsername.getValue();
        try {
            User user = UserModel.searchUser(userName);
            fillUserData(user);
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
