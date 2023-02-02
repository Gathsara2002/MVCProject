package lk.ijse.restaurant.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CrudNavigation {

    static AnchorPane pane;

    public static void crudNavigate(AnchorPane pane,String location) throws IOException {
        CrudNavigation.pane=pane;
        pane.getChildren().clear();
        Parent load = FXMLLoader.load(CrudNavigation.class.getResource("/lk/ijse/restaurant/view/" + location));
        pane.getChildren().add(load);
    }

}
