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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.restaurant.model.PaymentModel;
import lk.ijse.restaurant.model.SupplierModel;
import lk.ijse.restaurant.to.Payment;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdatePaymentFormController implements Initializable {
    public AnchorPane updatePaymentAP;
    public JFXTextField txtTotal;
    public JFXTextField txtType;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox cmbPid;

    public void updatePaymentOnAction(ActionEvent actionEvent) {
        String pid= String.valueOf(cmbPid.getValue());
        String date=lblDate.getText();
        String time=lblTime.getText();
        String type=txtType.getText();
        int total= Integer.parseInt(txtTotal.getText());

        Payment payment=new Payment(pid,date,time,type, total);

        try {
            boolean isUpdated= PaymentModel.updatePayment(payment);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Payment not updated !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(updatePaymentAP,"PaymentForm.fxml");
    }

    public void cmbPidOnAction(ActionEvent actionEvent) {
        String pid = String.valueOf(cmbPid.getValue());
        try {
            Payment payment=PaymentModel.searchPayment(pid);
            fillUserData(payment);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillUserData(Payment payment) {
        lblDate.setText(lblDate.getText());
        lblTime.setText(lblTime.getText());
        txtType.setText(payment.getType());
        txtTotal.setText(String.valueOf(payment.getTotal()));

    }

    private void setPaymentIds(){

        try {
            ObservableList<String> list= FXCollections.observableArrayList();
            ArrayList<String> ar= PaymentModel.loadPaymentIds();

            for (String code:ar) {
                list.add(code);
            }
            cmbPid.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPaymentIds();
        setTime();
        setDate();
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

}
