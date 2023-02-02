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
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.model.TableModel;
import lk.ijse.restaurant.to.Customer;
import lk.ijse.restaurant.to.Table;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    public AnchorPane customerAP;
    public TableView<Customer> tblCustomer;
    public TableColumn colCusId;
    public TableColumn colCusName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableColumn colEmpId;
    public Label lblTotalCustomers;

    public void addCustomerOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(customerAP,"AddCustomerForm.fxml");
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(customerAP,"DeleteCustomerForm.fxml");
    }

    public void editCustomerOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(customerAP,"UpdateCustomerForm.fxml");
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCustomerData();
        setCellFactoryValues();
        lblTotalCustomers.setText(String.valueOf(tblCustomer.getItems().size()));

    }

    private void loadCustomerData(){

        try {
            ObservableList<Customer> list= FXCollections.observableArrayList();
            ArrayList<Customer> ar= CustomerModel.loadCustomer();
            for (Customer data:ar) {
                list.add(data);
            }
            tblCustomer.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValues(){
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("eid"));
    }
}
