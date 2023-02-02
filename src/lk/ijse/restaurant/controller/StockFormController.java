package lk.ijse.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.OrderModel;
import lk.ijse.restaurant.model.StockModel;
import lk.ijse.restaurant.to.Order;
import lk.ijse.restaurant.to.Stock;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StockFormController implements Initializable {
    public AnchorPane stockAP;
    public TableView<Stock> tblStock;
    public TableColumn colStockId;
    public TableColumn colCatagory;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colStatus;
    public TableColumn colEmpId;

    public void addNewItemOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(stockAP,"AddStockForm.fxml");
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(stockAP,"DeleteStockForm.fxml");
    }

    public void editItemOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(stockAP,"UpdateStockForm.fxml");
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStockData();
        setCellFactoryValues();

    }

    private void loadStockData(){

        try {
            ObservableList<Stock> list= FXCollections.observableArrayList();
            ArrayList<Stock> stockList= StockModel.loadStock();
            for (Stock data:stockList) {
                list.add(data);
            }
            tblStock.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValues(){
        colStockId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCatagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("eid"));
    }
}
