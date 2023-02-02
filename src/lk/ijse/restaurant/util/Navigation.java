package lk.ijse.restaurant.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigation {
    private static AnchorPane pane;
    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route) {
            case ADMIN_DASHBOARD:
                window.setTitle("Admin Dashboard Form");
                initUI("AdminDashboardForm.fxml");
                break;
            case DASHBOARD:
                window.setTitle(" Dashboard Form");
                initUI("AdminDashboardForm.fxml");
                break;
            case AD_USER:
                window.setTitle("Users Form");
                initUI("AdUserForm.fxml");
                break;
            case AD_STAFF:
                window.setTitle("Staff Form");
                initUI("AdStaffForm.fxml");
                break;
            case AD_ORDERS:
                window.setTitle("Order Form");
                initUI("AdOrderForm.fxml");
                break;
            case LOGOUT:
                window.setTitle("Login Form");
                initUI("LoginForm.fxml");
                break;
            case AD_TABLES:
                window.setTitle("Table Form");
                initUI("AdTableForm.fxml");
                break;
            case STOCK:
                window.setTitle("Stock Form");
                initUI("StockForm.fxml");
                break;
            case SUPPLIER:
                window.setTitle("Supplier Form");
                initUI("SupplierForm.fxml");
                break;
            case PROFILE:
                window.setTitle("Profile Form");
                initUI("AdminProfileForm.fxml");
                break;
            case SETTING:
                window.setTitle("Setting Form");
                initUI("AdminSettingForm.fxml");
                break;
            case CASHIER_DASHBOARD:
                window.setTitle("Cashier Dashboard Form");
                initUI("CashierDashboardForm.fxml");
                break;
            case DASHBOARD2:
                window.setTitle(" Dashboard Form");
                initUI("CashierDashboardForm.fxml");
                break;
            case CUSTOMER:
                window.setTitle(" CustomerForm");
                initUI("CustomerForm.fxml");
                break;
            case CA_TABLE:
                window.setTitle(" Table Form");
                initUI("CashierTableForm.fxml");
                break;
            case CA_ORDERS:
                window.setTitle(" Order Form");
                initUI("CashierOrderForm.fxml");
                break;
            case CA_PROFILE:
                window.setTitle(" Cashier Profile Form");
                initUI("CashierProfileForm.fxml");
                break;
            case CA_SETTING:
                window.setTitle(" Cashier Setting Form");
                initUI("CashierSettingForm.fxml");
                break;
            case PRODUCTS:
                window.setTitle(" Products Form");
                initUI("ProductsForm.fxml");
                break;
            case PAYMENT:
                window.setTitle(" Payment Form");
                initUI("PaymentForm.fxml");
                break;
            case PLACE_ORDER:
                window.setTitle(" Place Order Form");
                initUI("PlaceOrderForm.fxml");
                break;
            case BILL:
                window.setTitle(" Bill Form");
                initUI("BillForm.fxml");
                break;


            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/restaurant/view/" + location)));
    }

}
