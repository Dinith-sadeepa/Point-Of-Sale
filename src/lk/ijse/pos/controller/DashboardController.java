package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.pos.common.LoadPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private ImageView cover;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cover.requestFocus();
    }

    @FXML
    void manageCustomerButtonAction(ActionEvent event) {
        LoadPane.loadPanel("/lk/ijse/pos/view/customer.fxml", event, this.getClass());
    }

    @FXML
    void manageItemButtonAction(ActionEvent event) {
        LoadPane.loadPanel("/lk/ijse/pos/view/item.fxml", event, this.getClass());
    }

    @FXML
    void placeOrderButtonAction(ActionEvent event) {
        LoadPane.loadPanel("/lk/ijse/pos/view/placeorder.fxml", event, this.getClass());
    }


    @FXML
    void reportButtonAction(ActionEvent event) {
        LoadPane.loadPanel("/lk/ijse/pos/view/reports.fxml", event, this.getClass());
    }

}
