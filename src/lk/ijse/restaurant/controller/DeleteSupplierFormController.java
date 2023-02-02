package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.EmployeeModel;
import lk.ijse.restaurant.model.SupplierModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Employee;
import lk.ijse.restaurant.to.Supplier;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteSupplierFormController implements Initializable {
    public AnchorPane deleteSupAP;
    public JFXTextField txtSupName;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtCompany;
    public JFXTextField txtItems;
    public JFXTextField txtPayment;
    public JFXComboBox cmbSupId;

    public void deleteSupplierOnAction(ActionEvent actionEvent) {
        String supId= String.valueOf(cmbSupId.getValue());
        Supplier supplier=new Supplier(supId);

        try {
            boolean isDeleted= SupplierModel.deleteSupplier(supplier);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted !").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Supplier not deleted !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(deleteSupAP,"SupplierForm.fxml");
    }

    public void cmbSupOnAction(ActionEvent actionEvent) {
        String supId = String.valueOf(cmbSupId.getValue());
        try {
            Supplier  suppliers=SupplierModel.searchSupplier(supId);
            fillUserData(suppliers);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setSupIds(){

        try {
            ObservableList<String> list= FXCollections.observableArrayList();
            ArrayList<String> ar= SupplierModel.loadSupplierIds();

            for (String code:ar) {
                list.add(code);
            }
            cmbSupId.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSupIds();
    }
    private void fillUserData(Supplier supplier) {
        txtSupName.setText(supplier.getName());
        txtContact.setText(String.valueOf(supplier.getContact()));
        txtEmail.setText(supplier.getEmail());
        txtCompany.setText(supplier.getCompany());
        txtItems.setText(String.valueOf(supplier.getNoOfItems()));
        txtPayment.setText(String.valueOf(supplier.getPayments()));
    }
}
