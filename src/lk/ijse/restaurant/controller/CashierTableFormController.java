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
import lk.ijse.restaurant.model.TableModel;
import lk.ijse.restaurant.to.Table;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CashierTableFormController implements Initializable {
    public AnchorPane CaTableAP;
    public TableView tblTable;
    public TableColumn colTid;
    public TableColumn colCapacity;
    public TableColumn colDesc;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colCid;
    public Label lblTotalTables;
    public TableColumn colAvailability;

    public void reserveTableOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(CaTableAP,"UpdateTableForm.fxml");
    }

    public void backOnAction(ActionEvent actionEvent) {

    }

    public void changeOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(CaTableAP,"UpdateTableForm.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableData();
        setCellFactoryValues();
        lblTotalTables.setText(String.valueOf(tblTable.getItems().size()));

    }

    private void loadTableData(){

        try {
            ObservableList<Table> list= FXCollections.observableArrayList();
            ArrayList<Table> ar= TableModel.loadTable();
            for (Table data:ar) {
                list.add(data);
            }
            tblTable.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValues(){
        colTid.setCellValueFactory(new PropertyValueFactory<>("tid"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("booking_date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("booking_time"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("cusId"));
    }
}
