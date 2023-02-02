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
import lk.ijse.restaurant.model.EmployeeModel;
import lk.ijse.restaurant.to.Employee;
import lk.ijse.restaurant.util.CrudNavigation;
import lk.ijse.restaurant.util.Navigation;
import lk.ijse.restaurant.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

public class AdStaffFormController implements Initializable {
    public AnchorPane AdStaffAP;
    public TableView tbalEmployee;
    public TableColumn colEid;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colNic;
    public TableColumn colSalary;
    public TableColumn colEmail;
    public TableColumn colPassword;
    public TableColumn ColDesignation;
    public TableColumn colAge;
    public Label lblTotalStaff;
    public TableColumn colGender;
    public TableColumn colUsername;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStaffData();
        setCellFactoryValue();
        lblTotalStaff.setText(String.valueOf(tbalEmployee.getItems().size()));
    }
    public void addNewEmpOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdStaffAP,"adAddStaffForm.fxml");
    }

    public void deleteEmpOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdStaffAP,"adDeleteStaffForm.fxml");
    }

    public void editEmpOnAction(ActionEvent actionEvent) throws IOException {
        CrudNavigation.crudNavigate(AdStaffAP,"AdUpdateStaffForm.fxml");
    }

    public void backDashboardOnAction(ActionEvent actionEvent) throws IOException {
    }

    private  void loadStaffData(){

        try {
            ObservableList<Employee> empList= FXCollections.observableArrayList();
            ArrayList<Employee> list= EmployeeModel.loadEmployee();
            for (Employee data:list) {
                empList.add(data);
            }
            tbalEmployee.setItems(empList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellFactoryValue(){
        colEid.setCellValueFactory(new PropertyValueFactory<>("eid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("passWord"));
        ColDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

}
