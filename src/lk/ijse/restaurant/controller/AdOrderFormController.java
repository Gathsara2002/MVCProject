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

public class AdOrderFormController implements Initializable {
    public AnchorPane AdOrderAP;
    public TableView<Order> tblOrders;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colOrderTime;
    public TableColumn colItems;
    public TableColumn colTotalFee;
    public TableColumn colPid;
    public TableColumn colCusId;
    public Label lblTotalOrders;

    public void addOrderOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdOrderAP,"AdAddOrderForm.fxml");
    }

    public void deleteOrderOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdOrderAP,"AdDeleteOrderForm.fxml");
    }

    public void editOrderOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdOrderAP,"AdUpdateOrderForm.fxml");
    }

    public void backOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadOrderData();
        setCellFactoryValues();
        try {
            lblTotalOrders.setText(String.valueOf(OrderModel.getOrderCount()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

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
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("oid"));
         colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrderTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colItems.setCellValueFactory(new PropertyValueFactory<>("noOfItems"));
        colTotalFee.setCellValueFactory(new PropertyValueFactory<>("total"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cid"));
    }

}
