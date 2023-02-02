package lk.ijse.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.ProductModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Products;
import lk.ijse.restaurant.to.User;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductsFormController implements Initializable {
    public AnchorPane productAP;
    public TableView tblProduct;
    public TableColumn colItemId;
    public TableColumn colName;
    public TableColumn colCategory;
    public TableColumn colPrice;
    public Label lblTotalProducts;
    public TableColumn colQty;

    public void addProductOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(productAP,"AddProductForm.fxml");
    }

    public void deleteProductOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(productAP,"DeleteProductForm.fxml");
    }

    public void editProductOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(productAP,"UpdateProductForm.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProductData();
        setCellFactoryValues();
        lblTotalProducts.setText(String.valueOf(tblProduct.getItems().size()));

    }

    private void loadProductData(){
        try {
            ObservableList<Products> list= FXCollections.observableArrayList();
            ArrayList<Products> userList= ProductModel.loadProducts();
            for (Products data:userList) {
                list.add(data);
            }
            tblProduct.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValues(){
        colItemId.setCellValueFactory(new PropertyValueFactory<>("fid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
}
