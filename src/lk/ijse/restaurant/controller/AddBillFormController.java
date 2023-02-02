package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.restaurant.model.BillModel;
import lk.ijse.restaurant.model.PaymentModel;
import lk.ijse.restaurant.to.Bill;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddBillFormController implements Initializable {
    public AnchorPane newBillAP;
    public JFXTextField txtBillNo;
    public JFXTextField txtPID;
    public JFXTextField txtDescription;
    public Label lblDate;
    public Label lblTime;

    public void addBillOnAction(ActionEvent actionEvent) {
        String billNo=txtBillNo.getText();
        String date=lblDate.getText();
        String time=lblTime.getText();
        String desc =txtDescription.getText();
        String pid=txtPID.getText();

        Bill bill=new Bill(billNo,date,time,desc,pid);

        try {
            boolean isAdded= BillModel.addBill(bill);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Bill  Added!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(newBillAP,"BillForm.fxml");
    }
    private void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setTime(){
        Timeline clock=new Timeline(new KeyFrame(Duration.ZERO, event ->{
            LocalTime currentTime=LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" +
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
