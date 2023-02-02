package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.StockModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Stock;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateStockFormController implements Initializable {
    public AnchorPane updateStockAP;
    public JFXTextField txtName;
    public JFXTextField txtCategory;
    public JFXTextField txtQty;
    public JFXTextField txtPrice;
    public JFXTextField txtStatus;
    public JFXTextField txtEmpId;
    public JFXComboBox cmbSid;

    public void updateOnAction(ActionEvent actionEvent) {
        String sid= String.valueOf(cmbSid.getValue());
        String itemName=txtName.getText();
        String cataogory=txtCategory.getText();
        int qty= Integer.parseInt(txtQty.getText());
        int unitPrice= Integer.parseInt(txtPrice.getText());
        String status= txtStatus.getText();
        String eid=txtEmpId.getText();

        Stock stock=new Stock(sid,itemName,cataogory,qty,unitPrice,status,eid);

        try {
            boolean isUpdated= StockModel.updateStock(stock);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Stock Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Stock not updated !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(updateStockAP,"StockForm.fxml");
    }

    public void cmbSidOnAction(ActionEvent actionEvent) {
        String sid = String.valueOf(cmbSid.getValue());
        try {
            Stock stock=StockModel.searchStock(sid);
            fillUserData(stock);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setStockIds(){

        try {
            ObservableList<String> list= FXCollections.observableArrayList();
            ArrayList<String> ar=StockModel.loadStockIds();

            for (String code:ar) {
                list.add(code);
            }
            cmbSid.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillUserData(Stock stock) {
        txtName.setText(stock.getItemName());
        txtCategory.setText(stock.getCategory());
        txtQty.setText(String.valueOf(stock.getQtyOnHand()));
        txtPrice.setText(String.valueOf(stock.getUnitPrice()));
        txtStatus.setText(stock.getStatus());
        txtEmpId.setText(stock.getEid());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       setStockIds();
    }
}
