package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.business.BOFactory;
import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.common.AlertBox;
import lk.ijse.pos.common.LoadPane;
import lk.ijse.pos.common.Validation;
import lk.ijse.pos.model.CustomerDTO;

import java.net.URL;
import java.util.ArrayList;
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
    private JFXButton addButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton deleteButton;


    @FXML
    private TableView<CustomerDTO> customerTable;

    private CustomerBO customerBO;
    private ObservableList<CustomerDTO> customerLst;

    public ManageCustomerController() {
        customerBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.CUSTOMER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadColumns();
        loadCustomers();
    }

    private void loadCustomers() {
        try {
            ArrayList<CustomerDTO> customerDTOS = (ArrayList<CustomerDTO>) customerBO.getAllCustomers();
            customerLst = FXCollections.observableArrayList(customerDTOS);
            customerTable.setItems(customerLst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadColumns() {
        customerTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerNic"));
        customerTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerContact"));
    }

    @FXML
    void addButtonAction(ActionEvent event) {
        if (checkName() || checkNIC() || checkContact()) {
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
                loadCustomers();
            }
        }
    }

    private boolean checkContact() {
        if (customerContactText.getText().trim().isEmpty()) {
            AlertBox.setAlert(Alert.AlertType.WARNING, "Customer Contact is Empty");
            return false;
        } else {
            if (!Validation.telephoneNoValidate(customerContactText.getText())) {
                AlertBox.setAlert(Alert.AlertType.WARNING, "Customer Contact is invalid");
                return false;
            }
        }
        return true;
    }

    private boolean checkNIC() {
        if (customerNicText.getText().trim().isEmpty()) {
            AlertBox.setAlert(Alert.AlertType.WARNING, "Customer NIC is Empty");
            return false;
        } else {
            if (!Validation.nicValidate(customerNicText.getText())) {
                AlertBox.setAlert(Alert.AlertType.WARNING, "Customer nic is invalid");
                return false;
            }
        }
        return true;
    }

    private boolean checkName() {
        if (customerNameText.getText().trim().isEmpty()) {
            AlertBox.setAlert(Alert.AlertType.WARNING, "Customer Name is Empty");
            return false;
        } else {
            if (!Validation.nameValidate(customerNameText.getText())) {
                AlertBox.setAlert(Alert.AlertType.WARNING, "Customer Name is invalid");
                return false;
            }
        }
        return true;
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
        if (isDeleted) {
            AlertBox.setAlert(Alert.AlertType.INFORMATION, "Customer Deleted");
            loadCustomers();
            addButton.setDisable(false);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }

    @FXML
    void updateButtonAction(ActionEvent event) {
        if (checkName() || checkNIC() || checkContact()) {
            CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(customerIdText.getText()), customerNameText.getText(), customerNicText.getText(), Integer.parseInt(customerContactText.getText()));
            boolean isUpdated = false;
            try {
                isUpdated = customerBO.updateCustomer(customerDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isUpdated) {
                AlertBox.setAlert(Alert.AlertType.INFORMATION, "Customer Updated");
                loadCustomers();
                addButton.setDisable(false);
                updateButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        }
    }

    @FXML
    void customerTableClicked(MouseEvent event) {
        CustomerDTO selectedItem = customerTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            customerNameText.setText(selectedItem.getCustomerName());
            customerContactText.setText(selectedItem.getCustomerContact() + "");
            customerIdText.setText(selectedItem.getCustomerId() + "");
            customerNicText.setText(selectedItem.getCustomerNic());
            addButton.setDisable(true);
            updateButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }
}
