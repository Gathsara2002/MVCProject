package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.SupplierModel;
import lk.ijse.restaurant.model.UserModel;
import lk.ijse.restaurant.to.Supplier;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.sql.SQLException;

public class AddSupplierFormController {
    public AnchorPane newSupAP;
    public JFXTextField txtSupId;
    public JFXTextField txtSupName;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtCompany;
    public JFXTextField txtItems;
    public JFXTextField txtPayment;

    public void addSupplierOnAction(ActionEvent actionEvent) {
        String supId=txtSupId.getText();
        String name=txtSupName.getText();
        int contact= Integer.parseInt(txtContact.getText());
        String email=txtEmail.getText();
        String company=txtCompany.getText();
        int noOfItems= Integer.parseInt(txtItems. getText());
        Double payment= Double.valueOf(txtPayment.getText());

        Supplier supplier=new Supplier(supId ,name,contact,email,company,noOfItems,payment);

        try {
            boolean isAdded= SupplierModel.addSupplier(supplier);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(newSupAP,"SupplierForm.fxml");
    }
}
