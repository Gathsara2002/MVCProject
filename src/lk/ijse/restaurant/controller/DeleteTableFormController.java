package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.model.TableModel;
import lk.ijse.restaurant.to.Customer;
import lk.ijse.restaurant.to.Products;
import lk.ijse.restaurant.to.Table;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteTableFormController implements Initializable {
    public JFXTextField txtCapacity;
    public JFXTextField txtDesc;
    public JFXTextField txtCid;
    public JFXTextField txtAvailability;
    public DatePicker datePicker;
    public JFXTextField txtTime;
    public ComboBox cmbTid;
    public AnchorPane deleteTableAP;

    public void deleteTableOnAction(ActionEvent actionEvent) {
        String tid= String.valueOf(cmbTid.getValue());
       Table table=new Table(tid);

        try {
            boolean isDeleted= TableModel.deleteTable(table);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Table Deleted !").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Table not deleted !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(deleteTableAP,"AdTableForm.fxml");
    }

    public void cmbtidOnAction(ActionEvent actionEvent) {
        String tid = String.valueOf(cmbTid.getValue());
        try {
            Table table=TableModel.searchTable(tid);
            fillUserData(table);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillUserData(Table table) {
        txtAvailability.setText(table.getAvailability());
        txtCapacity.setText(String.valueOf(table.getCapacity()));
        txtDesc.setText(table.getDescription());
        txtTime.setText(table.getBooking_time());
        datePicker.setValue(LocalDate.parse(table.getBooking_date()));
        txtCid.setText(table.getCusId());
    }

    private void setTableIds(){

        try {
            ObservableList<String> list= FXCollections.observableArrayList();
            ArrayList<String> ar=TableModel.loadTableIds();

            for (String code:ar) {
                list.add(code);
            }
            cmbTid.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableIds();
    }


}
