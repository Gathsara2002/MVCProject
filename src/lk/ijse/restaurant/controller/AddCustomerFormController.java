package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.model.EmployeeModel;
import lk.ijse.restaurant.to.Customer;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.sql.SQLException;

public class AddCustomerFormController {
    public AnchorPane newCustomerAP;
    public JFXTextField txtCid;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtEid;

    public void addCustomerOnAction(ActionEvent actionEvent) {
        String cid=txtCid.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        int contact= Integer.parseInt(txtContact.getText());
        String email=txtEmail.getText();
        String eid= txtEid.getText();

        Customer customer=new Customer(cid,name,address,contact,email,eid);

        try {
            boolean isAdded= CustomerModel.addCustomer(customer);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer  Added!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(newCustomerAP,"CustomerForm.fxml");
    }
}
