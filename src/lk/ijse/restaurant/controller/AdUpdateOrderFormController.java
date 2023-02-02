package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.restaurant.model.EmployeeModel;
import lk.ijse.restaurant.model.OrderModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Employee;
import lk.ijse.restaurant.to.Order;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdUpdateOrderFormController implements Initializable {
    public AnchorPane updateOrderAP;
    public JFXTextField txtOrTime;
    public JFXTextField txtItems;
    public JFXTextField txtTotal;
    public JFXTextField txtPid;
    public JFXTextField txtCid;
    public JFXComboBox cmbOid;
    public JFXTextField txtOrDate;

    public void updateOnAction(ActionEvent actionEvent) {
        String oid= String.valueOf(cmbOid.getValue());
        String date=txtOrDate.getText();
        String time =txtOrTime.getText();
        int noOfItems= Integer.parseInt(txtItems.getText());
        int total= Integer.parseInt(txtTotal.getText());
        String pid=txtPid.getText();
        String cid=txtCid.getText();

        Order order=new Order(oid,date,time,noOfItems,total,pid,cid);


        try {
            boolean isUpdated= OrderModel.updateOrder(order);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order not updated !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(updateOrderAP,"AdOrderForm.fxml");
    }

    public void cmbOidOnAction(ActionEvent actionEvent) {
        String oid = String.valueOf(cmbOid.getValue());
        try {
            Order order= OrderModel.searchUser(oid);
            fillUserData(order);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setOrderIds(){

        try {
            ObservableList<String> list= FXCollections.observableArrayList();
            ArrayList<String> ar=OrderModel.loadOrderIds();

            for (String code:ar) {
                list.add(code);
            }
            cmbOid.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillUserData(Order order) {
        txtOrDate.setText(order.getDate());
        txtOrTime.setText(order.getTime());
        txtItems.setText(String.valueOf(order.getNoOfItems()));
        txtTotal.setText(String.valueOf(order.getTotal()));
        txtCid.setText(order.getCid());
        txtPid.setText(order.getPid());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOrderIds();
        setTime();
        setDate();
    }


    private void setDate(){
        txtOrDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            LocalTime currentTime = LocalTime.now();
            txtOrTime.setText(currentTime.getHour() + ":" +
                    currentTime.getMinute() + ":" +
                    currentTime.getSecond());

        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

}
