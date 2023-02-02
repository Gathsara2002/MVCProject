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
import lk.ijse.restaurant.util.Navigation;
import lk.ijse.restaurant.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdTableFormController implements Initializable {
    public AnchorPane AdTableAP;
  
    public TableView tblTable;
    public TableColumn colTableId;
    public TableColumn colCapacity;
    public TableColumn colDesc;
    public TableColumn colDate;
    public TableColumn colTime;
    public Label lblTotalTables;
    public TableColumn colAvailability;
    public TableColumn colCusId;


    public void addTableOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdTableAP,"AddTableForm.fxml");
    }

    public void deleteTableOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdTableAP,"DeleteTableForm.fxml");
    }

    public void editTableOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdTableAP,"UpdateTableForm.fxml");
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
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
        colTableId.setCellValueFactory(new PropertyValueFactory<>("tid"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("booking_date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("booking_time"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
    }
}
