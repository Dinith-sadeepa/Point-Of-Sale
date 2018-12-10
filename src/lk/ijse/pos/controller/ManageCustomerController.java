package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.business.BOFactory;
import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.common.AlertBox;
import lk.ijse.pos.common.LoadPane;
import lk.ijse.pos.model.CustomerDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCustomerController implements Initializable {

    @FXML
    private TextField customerNameText;

    @FXML
    private TextField customerNicText;

    @FXML
    private TextField customerContactText;

    @FXML
    private TextField customerIdText;

    @FXML
    private TableView<?> customerTable;

    private CustomerBO customerBO;

    public ManageCustomerController() {
        customerBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.CUSTOMER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addButtonAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(customerNameText.getText(), customerNicText.getText(), Integer.parseInt(customerContactText.getText()));
        boolean isAdded = false;
        try {
            isAdded = customerBO.addCustomer(customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isAdded) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Customer Added", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
        }
    }

    @FXML
    void customerContactTextAction(ActionEvent event) {
//        addButtonAction(event);
    }

    @FXML
    void customerIdTextAction(ActionEvent event) {
        customerNameText.requestFocus();
    }

    @FXML
    void customerNameTextAction(ActionEvent event) {
        customerNicText.requestFocus();
    }

    @FXML
    void customerNicTextAction(ActionEvent event) {
        customerContactText.requestFocus();
    }

    @FXML
    void dashboardLabelClicked(MouseEvent event) {
        LoadPane.loadPanel("/lk/ijse/pos/view/dashboard.fxml", event, this.getClass());
    }

    @FXML
    void deleteButtonAction(ActionEvent event) {
        boolean isDeleted = false;
        try {
            isDeleted = customerBO.deleteCustomer(Integer.parseInt(customerIdText.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(isDeleted){
            AlertBox.setAlert(Alert.AlertType.INFORMATION, "Customer Deleted");
        }
    }

    @FXML
    void updateButtonAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(customerIdText.getText()),customerNameText.getText(), customerNicText.getText(), Integer.parseInt(customerContactText.getText()));
        boolean isUpdated = false;
        try {
            isUpdated = customerBO.updateCustomer(customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(isUpdated){
            AlertBox.setAlert(Alert.AlertType.INFORMATION, "Customer Updated");
        }
    }
}
