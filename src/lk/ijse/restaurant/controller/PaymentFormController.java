package lk.ijse.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.PaymentModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Payment;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {
    public AnchorPane paymentAP;
    public TableView tblPayment;
    public TableColumn colPid;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colType;
    public TableColumn colTotal;

    public void addPaymentOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(paymentAP,"AddPaymentForm.fxml");
    }

    public void deletePaymentOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(paymentAP,"DeletePaymentForm.fxml");
    }

    public void editPaymentOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(paymentAP,"UpdatePaymentForm.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPaymentData();
        setCellFactoryValues();

    }
    private void loadPaymentData(){
        try {
            ObservableList<Payment> list= FXCollections.observableArrayList();
            ArrayList<Payment> payList= PaymentModel.loadPayment();
            for (Payment data:payList) {
                list.add(data);
            }
            tblPayment.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValues(){
        colPid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }
}
