package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.model.StockModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Customer;
import lk.ijse.restaurant.to.Stock;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteCustomerFormController implements Initializable {
    public AnchorPane deleteCustomerAP;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtEid;
    public JFXComboBox cmbCid;

    public void deleteCustomerOnAction(ActionEvent actionEvent) {
        String cid= String.valueOf(cmbCid.getValue());
        Customer customer=new Customer(cid);

        try {
            boolean isDeleted= CustomerModel.deleteCustomer(customer);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted !").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Customer not deleted !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(deleteCustomerAP,"CustomerForm.fxml");
    }

    public void cmbCidOnAction(ActionEvent actionEvent) {
        String cid = String.valueOf(cmbCid.getValue());
        try {
            Customer customer=CustomerModel.searchCustomer(cid);
            fillUserData(customer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCustomerIds(){

        try {
            ObservableList<String> list= FXCollections.observableArrayList();
            ArrayList<String> ar=CustomerModel.loadCustomerIds();

            for (String code:ar) {
                list.add(code);
            }
            cmbCid.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillUserData(Customer customer) {
        txtEid.setText(customer.getEid());
        txtAddress.setText(customer.getAddress());
        txtContact.setText(String.valueOf(customer.getContact()));
        txtEmail.setText(customer.getEmail());
        txtName.setText(customer.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCustomerIds();
    }
}
