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
import lk.ijse.restaurant.model.OrderModel;
import lk.ijse.restaurant.to.Order;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CashierOrderFormController implements Initializable {
    public AnchorPane CaOrderAP;
    public TableView<Order> tblOrders;
    public TableColumn colOid;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colItems;
    public TableColumn colTotal;
    public TableColumn colPid;
    public TableColumn colCid;
    public Label lblTotalOrders;

    public void addOrderOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(CaOrderAP,"AdAddOrderForm.fxml");
    }

    public void deleteOrderOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(CaOrderAP,"AdDeleteOrderForm.fxml");
    }

    public void editOrderOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(CaOrderAP,"AdUpdateOrderForm.fxml");
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOrderData();
        setCellFactoryValues();

    }

    private void loadOrderData(){

        try {
            ObservableList<Order> list= FXCollections.observableArrayList();
            ArrayList<Order> orderList= OrderModel.loadOrder();
            for (Order data:orderList) {
                list.add(data);
            }
            tblOrders.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValues(){

        colOid.setCellValueFactory(new PropertyValueFactory<>("oid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colItems.setCellValueFactory(new PropertyValueFactory<>("noOfItems"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("cid"));
    }
}
