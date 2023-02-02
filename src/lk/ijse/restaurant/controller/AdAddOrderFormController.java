package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
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
import java.util.ResourceBundle;

public class AdAddOrderFormController implements Initializable {

    public JFXTextField txtOid;
    public JFXTextField txtOrDate;
    public JFXTextField txtOrTime;
    public JFXTextField txtItems;
    public JFXTextField txtTotal;
    public JFXTextField txtPid;
    public JFXTextField txtCid;
    public AnchorPane newOrderAP;

    public void addOrderOnAction(ActionEvent actionEvent) {
        String oid= txtOid.getText();
        String date= txtOrDate.getText();
        String time= txtOrTime.getText();
        int noOfItems= Integer.parseInt(txtItems.getText());
        int total= Integer.parseInt(txtTotal.getText());
        String pid=txtPid.getText();
        String cid=txtCid.getText();

        Order order=new Order(oid,date,time,noOfItems,total,pid,cid);

        try {
            boolean isAdded= OrderModel.addOrder(order);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Added!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(newOrderAP,"AdOrderForm.fxml");
    }
    private void setDate(){
        txtOrDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setTime(){
        Timeline clock=new Timeline(new KeyFrame(Duration.ZERO, event ->{
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDate();
        setTime();
    }
}
