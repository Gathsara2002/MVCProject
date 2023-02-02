package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import lk.ijse.restaurant.model.CustomerModel;
import lk.ijse.restaurant.model.TableModel;
import lk.ijse.restaurant.to.Table;
import lk.ijse.restaurant.util.CrudNavigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddTableFormController implements Initializable {
    public AnchorPane newTableAP;
    public JFXTextField txtTid;
    public JFXTextField txtCapacity;
    public JFXTextField txtDesc;
    public JFXTextField txtCid;
    public JFXTextField txtAvailability;
    public DatePicker datePicker;
    public JFXTextField txtTime;


    public void addTableOnAction(ActionEvent actionEvent) {
        String tid=txtTid.getText();
        int capacity= Integer.parseInt(txtCapacity.getText());
        String desc=txtDesc.getText();
        String date= String.valueOf(datePicker.getValue());
        String time=txtTime.getText();
        String availability=txtAvailability.getText();
        String cusId=txtCid.getText();

        Table table=new Table(tid,capacity,desc,date,time,availability,cusId);

        try {
            boolean isAdded= TableModel.addTable(table);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Table  Added!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(newTableAP,"AdTableForm.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
