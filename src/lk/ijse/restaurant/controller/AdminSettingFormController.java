package lk.ijse.restaurant.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class AdminSettingFormController {
    public AnchorPane settingAP;
    public JFXTextField txtUsername;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtNic;
    public JFXTextField txtRole;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXRadioButton rbtnMale;
    public JFXRadioButton rbtnFemale;

    public void cbShowPW(ActionEvent actionEvent) {
    }

    public void saveChangesOnAction(ActionEvent actionEvent) {
    }
}
