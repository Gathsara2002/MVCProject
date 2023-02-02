package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.ProductModel;
import lk.ijse.restaurant.model.StockModel;
import lk.ijse.restaurant.to.Products;
import lk.ijse.restaurant.to.Stock;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.sql.SQLException;

public class AddProductFormController {
    public JFXTextField txtFid;
    public JFXTextField txtItemName;
    public JFXTextField txtCategory;
    public JFXTextField txtQty;
    public JFXTextField txtItemPrice;
    public AnchorPane newProductAP;

    public void addProductInAction(ActionEvent actionEvent) {
       String fid=txtFid.getText();
       String itemName=txtItemName.getText();
       String category=txtCategory.getText();
       int itemPrice= Integer.parseInt(txtItemPrice.getText());
       int qty= Integer.parseInt(txtQty.getText());

        Products products=new Products(fid,itemName,category,itemPrice,qty);

        try {
            boolean isAdded= ProductModel.addProduct(products);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Product  Added!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(newProductAP,"ProductsForm.fxml");
    }
}
