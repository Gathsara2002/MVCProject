package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.ProductModel;
import lk.ijse.restaurant.model.SupplierModel;
import lk.ijse.restaurant.to.Products;
import lk.ijse.restaurant.to.Supplier;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateProductFormController implements Initializable {
    public AnchorPane updateProductAP;
    public JFXTextField txtItemName;
    public JFXTextField txtCategory;
    public JFXTextField txtQty;
    public JFXTextField txtItemPrice;
    public ComboBox cmbFId;

    public void updateProductOnAction(ActionEvent actionEvent) {
        String fid= String.valueOf(cmbFId.getValue());
        String itemName=txtItemName.getText();
        String category=txtCategory.getText();
        int itemPrice= Integer.parseInt(txtItemPrice.getText());
        int qty= Integer.parseInt(txtQty.getText());

        Products products=new Products(fid,itemName,category,itemPrice,qty);
        try {
            boolean isUpdated= ProductModel.updateProduct(products);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Product  Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product not updated !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(updateProductAP,"ProductsForm.fxml");
    }

    public void cmbFidOnAction(ActionEvent actionEvent) {
        String fid = String.valueOf(cmbFId.getValue());
        try {
            Products products=ProductModel.searchProducts(fid);
            fillUserData(products);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setProductsIds(){

        try {
            ObservableList<String> list= FXCollections.observableArrayList();
            ArrayList<String> ar=ProductModel.loadFids();

            for (String code:ar) {
                list.add(code);
            }
            cmbFId.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillUserData(Products products) {
        txtItemName.setText(products.getItemName());
        txtCategory.setText(products.getCategory());
        txtItemPrice.setText(String.valueOf(products.getItemPrice()));
        txtQty.setText(String.valueOf(products.getQty()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setProductsIds();
    }
}
