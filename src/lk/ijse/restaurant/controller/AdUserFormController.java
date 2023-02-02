package lk.ijse.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;
import lk.ijse.restaurant.util.Navigation;
import lk.ijse.restaurant.util.Routes;
import netscape.security.UserTarget;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdUserFormController implements Initializable {
    public AnchorPane AdUserAP;
    public  TableView<User> tblUser;
    public TableColumn col_userName;
    public TableColumn col_name;
    public TableColumn col_address;
    public TableColumn col_contact;
    public TableColumn col_nic;
    public TableColumn col_email;
    public TableColumn col_passWord;
    public TableColumn col_role;
    public Label lblTotalUsers;

    public void addUserOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdUserAP,"adAddUserForm.fxml");
    }

    public void deleteUserOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdUserAP,"adDeleteUserForm.fxml");
    }

    public void editUserOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdUserAP,"AdUpdateUserForm.fxml");
    }

    public void backDBOnAction(ActionEvent actionEvent) throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserData();
        setCellFactoryValues();
        lblTotalUsers.setText(String.valueOf(tblUser.getItems().size()));

    }

    private void loadUserData(){
        try {
            ObservableList<User> list=FXCollections.observableArrayList();
            ArrayList<User> userList=UserModel.loadUser();
            for (User data:userList) {
                list.add(data);
            }
            tblUser.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValues(){
        col_userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        col_nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_passWord.setCellValueFactory(new PropertyValueFactory<>("passWord"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

}
