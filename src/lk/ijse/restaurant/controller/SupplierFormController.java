package lk.ijse.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.SupplierModel;
import lk.ijse.restaurant.to.Supplier;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    public AnchorPane supplierAP;
    public TableView<Supplier> tblSuppliers;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableColumn colCompany;
    public TableColumn coNoOfItems;
    public TableColumn colPayments;
    public Label lblTotalSuppliers;

    public void addSupplierOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(supplierAP,"AddSupplierForm.fxml");
    }

    public void deleteSupplierOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(supplierAP,"DeleteSupplierForm.fxml");
    }

    public void editSupplierOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(supplierAP,"UpdateSupplierForm.fxml");
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSupplierData();
        setCellFactoryValues();
        lblTotalSuppliers.setText(String.valueOf(tblSuppliers.getItems().size()));

    }

    private void loadSupplierData(){

        try {
            ObservableList<Supplier> list= FXCollections.observableArrayList();
            ArrayList<Supplier> supList= SupplierModel.loadSuppliers();
            for (Supplier data:supList) {
                list.add(data);
            }
            tblSuppliers.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValues(){
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        coNoOfItems.setCellValueFactory(new PropertyValueFactory<>("noOfItems"));
        colPayments.setCellValueFactory(new PropertyValueFactory<>("payments"));
    }

}
