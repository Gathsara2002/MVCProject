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
import lk.ijse.restaurant.to.Customer;
import lk.ijse.restaurant.to.Stock;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateCustomerFormController implements Initializable {
    public AnchorPane updateCustomerAP;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtEid;
    public JFXComboBox cmbCid;

    public void updateCustomerOnAction(ActionEvent actionEvent) {
        String cid= String.valueOf( cmbCid.getValue());
        String name=txtName.getText();
        String address=txtAddress.getText();
        int contact= Integer.parseInt(txtContact.getText());
        String email= txtEmail.getText();
        String eid=txtEid.getText();

        Customer customer=new Customer(cid,name,address,contact,email,eid);

        try {
            boolean isUpdated= CustomerModel.updateCustomer(customer);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not updated !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(updateCustomerAP,"CustomerForm.fxml");
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
