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
import lk.ijse.restaurant.model.SupplierModel;
import lk.ijse.restaurant.model.TableModel;
import lk.ijse.restaurant.to.Supplier;
import lk.ijse.restaurant.to.Table;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateTableFormController implements Initializable {
    public AnchorPane updateTableAP;
    public JFXTextField txtCapacity;
    public JFXTextField txtDesc;
    public JFXTextField txtCid;
    public JFXTextField txtAvailability;
    public DatePicker datePicker;
    public JFXTextField txtTime;
    public ComboBox cmbTid;

    public void updateTableOnAction(ActionEvent actionEvent) {
        String tid= String.valueOf(cmbTid.getValue());
        int capacity= Integer.parseInt(txtCapacity.getText());
        String desc=txtDesc.getText();
        String date= String.valueOf(datePicker.getValue());
        String time=txtTime.getText();
        String availability=txtAvailability.getText();
        String cid=txtCid.getText();

        Table table=new Table(tid,capacity,desc,date,time,availability,cid);
        try {
            boolean isUpdated= TableModel.updateTable(table);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Table Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Table not updated !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(updateTableAP,"AdTableForm.fxml");
    }

    public void cmbTidOnAction(ActionEvent actionEvent) {
        String tid = String.valueOf(cmbTid.getValue());
        try {
           Table table=TableModel.searchTable(tid);
            fillUserData(table);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
    private void fillUserData(Table table) {
        txtAvailability.setText(table.getAvailability());
        txtCapacity.setText(String.valueOf(table.getCapacity()));
        txtDesc.setText(table.getDescription());
        txtTime.setText(table.getBooking_time());
        datePicker.setValue(LocalDate.parse(table.getBooking_date()));
        txtCid.setText(table.getCusId());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableIds();
    }
}
