package lk.ijse.restaurant.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.restaurant.model.*;
import lk.ijse.restaurant.to.Employee;
import lk.ijse.restaurant.util.Navigation;
import lk.ijse.restaurant.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AdminDashboardFormController implements Initializable {
    public AnchorPane adminAP;
    public Label lblDate;
    public Label lblTime;
    public  AnchorPane dashboardAP;
    public Label lblEarnings;
    public Label lblCustomer;
    public Label lblTable;
    public Label lblOrders;
    public Label lblUsers;
    public BarChart salaryChart;
    public CategoryAxis name;
    public NumberAxis amount;

    public void adDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,adminAP);
    }

    public void adUserOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AD_USER,dashboardAP);
    }

    public void adStaffOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AD_STAFF,dashboardAP);
    }

    public void adProfileOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PROFILE,dashboardAP);
    }

    public void adReportsOnAction(ActionEvent actionEvent) {
    }

    public void adProductOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PRODUCTS,dashboardAP);
    }

    public void adTableOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AD_TABLES,dashboardAP);
    }

    public void adStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STOCK,dashboardAP);
    }

    public void adSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER,dashboardAP);
    }

    public void adLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGOUT,adminAP);
    }

//    public void adSettingOnAction(ActionEvent actionEvent) throws IOException {
//        Navigation.navigate(Routes.SETTING,dashboardAP);
//    }

    public void customerOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,dashboardAP);
    }

    public void earningsOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT,dashboardAP);
    }

    public void tableOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.AD_TABLES,dashboardAP);
    }

    public void ordersOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.AD_ORDERS,dashboardAP);
    }

    public void adOrdersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AD_ORDERS,dashboardAP);
    }
    public void onUserClickAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.AD_USER,dashboardAP);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblUsers.setText(String.valueOf(setUserCount()));
        lblTable.setText(String.valueOf(setTableCount()));
        lblOrders.setText(String.valueOf(setOrderCount()));
        lblCustomer.setText(String.valueOf(setCustomerCount()));
        setDate();
        setTime();
        lblEarnings.setText("");

        XYChart.Series series1=new XYChart.Series();
        series1.getData().add(new XYChart.Data("Manager",100000));
        series1.getData().add(new XYChart.Data("Cashier",50000));
        series1.getData().add(new XYChart.Data("Chef",70000));
        series1.getData().add(new XYChart.Data("Waiter",45000));
        series1.getData().add(new XYChart.Data("Cleaner",25000));
        series1.getData().add(new XYChart.Data("Kitchen Hand",35000));
        series1.getData().add(new XYChart.Data("2nd Chef",60000));
        series1.getData().add(new XYChart.Data("Bartender",55000));
        salaryChart.getData().add(series1);

    }

    public int setUserCount(){
        try {
            int x= UserModel.getUerCount();
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

    public int setCustomerCount() {
        try {
            int x = CustomerModel.getCustomerCount();
            return x;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setTime(){
        Timeline clock=new Timeline(new KeyFrame(Duration.ZERO,event ->{
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

    public void supplyDetailOnAction(ActionEvent actionEvent) {
    }


    public void onEarningClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT,dashboardAP);
    }
}

