package lk.ijse.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.BillModel;
import lk.ijse.restaurant.model.PaymentModel;
import lk.ijse.restaurant.to.Bill;
import lk.ijse.restaurant.to.Payment;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BillFormController implements Initializable {
    public AnchorPane billAP;
    public TableView tblBill;
    public TableColumn colBillNo;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colDesc;
    public TableColumn colPid;

    public void addBillOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(billAP,"AddBillForm.fxml");
    }

    public void generateBillOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBillData();
        setCellFactoryValues();
    }

    private void loadBillData(){
        try {
            ObservableList<Bill> list= FXCollections.observableArrayList();
            ArrayList<Bill> payList= BillModel.loadBill();
            for (Bill data:payList) {
                list.add(data);
            }
            tblBill.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValues(){
        colBillNo.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("pid"));

    }
}
