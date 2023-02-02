package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.model.ProductModel;
import lk.ijse.restaurant.to.Customer;
import lk.ijse.restaurant.to.Products;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteProductFormController implements Initializable {
    public AnchorPane deleteProductAP;
    public JFXTextField txtItemName;
    public JFXTextField txtCategory;
    public JFXTextField txtQty;
    public JFXTextField txtItemPrice;
    public ComboBox cmbFId;

    public void deleteProductOnAction(ActionEvent actionEvent) {
        String fid= String.valueOf(cmbFId.getValue());
        Products products=new Products(fid);

        try {
            boolean isDeleted= ProductModel.deleteProduct(products);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Product Deleted !").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Product not deleted !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(deleteProductAP,"ProductsForm.fxml");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setProductsIds();
    }

    private void fillUserData(Products products) {
       txtItemName.setText(products.getItemName());
       txtCategory.setText(products.getCategory());
       txtItemPrice.setText(String.valueOf(products.getItemPrice()));
       txtQty.setText(String.valueOf(products.getQty()));
    }

}
