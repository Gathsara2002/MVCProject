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
import lk.ijse.restaurant.model.OrderModel;
import lk.ijse.restaurant.model.UserModel;
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

public class AdDeleteOrderFormController implements Initializable {


    public AnchorPane deleteOrderAP;
    public JFXTextField txtOrDate;
    public JFXTextField txtOrTime;
    public JFXTextField txtItems;
    public JFXTextField txtTotal;
    public JFXTextField txtPid;
    public JFXTextField txtCid;
    public JFXComboBox cmbOid;

    public void deleteOrderOnAction(ActionEvent actionEvent) {
        String oid= String.valueOf(cmbOid.getValue());
        Order order=new Order(oid);

        try {
            boolean isDeleted= OrderModel.deleteOrder(order);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Deleted !").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Order not deleted !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(deleteOrderAP,"AdOrderForm.fxml");
    }

    public void cmbOidOnAction(ActionEvent actionEvent) {
        String oid = cmbOid.getValue().toString();
        try {
            Order order=OrderModel.searchUser(oid);
            fillUserData(order);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDate();
        setTime();
        setOrderIds();
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
        txtOrDate.setText(txtOrDate.getText());
        txtOrTime.setText(txtOrTime.getText());
        txtItems.setText(String.valueOf(order.getNoOfItems()));
        txtCid.setText(order.getCid());
        txtPid.setText(order.getPid());
    }

    private void setDate(){
        txtOrDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setTime(){
        Timeline clock=new Timeline(new KeyFrame(Duration.ZERO,event ->{
            LocalTime currentTime=LocalTime.now();
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
