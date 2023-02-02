package lk.ijse.restaurant.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.model.OrderModel;
import lk.ijse.restaurant.model.TableModel;
import lk.ijse.restaurant.util.Navigation;
import lk.ijse.restaurant.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NavigableMap;
import java.util.ResourceBundle;

public class CashierDashboardFormController implements Initializable {
    public AnchorPane cashierDashboardAP;
    public Label lblDate;
    public Label lblTime;
    public Label lblCustomer;
    public Label lblEarnings;
    public Label lblTable;
    public Label lblOrders;
    public AnchorPane dashBoardCashierAP;

    public void caCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,dashBoardCashierAP);
    }

    public void caProductOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PRODUCTS,dashBoardCashierAP);
    }

    public void caTableOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CA_TABLE,dashBoardCashierAP);
    }

    public void caOrdersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CA_ORDERS,dashBoardCashierAP);
    }

    public void caBillOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.BILL,dashBoardCashierAP);
    }

    public void caPaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT,dashBoardCashierAP);
    }

    public void caProfileOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CA_PROFILE,dashBoardCashierAP);
    }

    public void caSettingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CA_SETTING,dashBoardCashierAP);
    }

    public void customerOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,dashBoardCashierAP);
    }

    public void earningsOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT,dashBoardCashierAP);
    }

    public void tableOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CA_TABLE,dashBoardCashierAP);
    }

    public void ordersOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CA_ORDERS,dashBoardCashierAP);
    }

    public void caDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD2,cashierDashboardAP);
    }

    public void caLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGOUT,cashierDashboardAP);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblCustomer.setText(String.valueOf(setCustomerCount()));
        lblTable.setText(String.valueOf(setTableCount()));
        lblOrders.setText(String.valueOf(setOrderCount()));
        setDate();
        setTime();

    }

    public int setCustomerCount(){
        try {
            int x= CustomerModel.getCustomerCount();
            return x;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int setTableCount(){
        try {
            int x= TableModel.getTableCount();
            return x;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int setOrderCount(){
        try {
            int x= OrderModel.getOrderCount();
            return x;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACE_ORDER,dashBoardCashierAP);
    }

    public void onEarningClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT,dashBoardCashierAP);
    }
}
