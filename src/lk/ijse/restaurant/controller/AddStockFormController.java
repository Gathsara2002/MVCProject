package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.StockModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Stock;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.sql.SQLException;

public class AddStockFormController {
    public AnchorPane newStockAp;
    public JFXTextField txtSid;
    public JFXTextField txtName;
    public JFXTextField txtCategory;
    public JFXTextField txtQty;
    public JFXTextField txtPrice;
    public JFXTextField txtStatus;
    public JFXTextField txtEmpId;

    public void addStockOnAction(ActionEvent actionEvent) {

        String sid=txtSid.getText();
        String itemName=txtName.getText();
        String category=txtCategory.getText();
        int qty= Integer.parseInt(txtQty.getText());
        int unitPrice= Integer.parseInt(txtPrice.getText());
        String status=txtStatus.getText();
        String eid=txtEmpId.getText();

        Stock stock =new Stock(sid,itemName,category,qty,unitPrice,status,eid);

        try {
            boolean isAdded= StockModel.addStock(stock);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Item  Added!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(newStockAp,"StockForm.fxml");
    }
}
