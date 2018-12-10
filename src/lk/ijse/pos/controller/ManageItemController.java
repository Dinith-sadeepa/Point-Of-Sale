package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.business.BOFactory;
import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.common.AlertBox;
import lk.ijse.pos.common.LoadPane;
import lk.ijse.pos.common.Validation;
import lk.ijse.pos.model.ItemDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageItemController implements Initializable {

    @FXML
    private TextField itemDescriptionText;

    @FXML
    private TextField itemQtyText;

    @FXML
    private TextField itemUnitPriceText;

    @FXML
    private TextField itemCodeText;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private TableView<ItemDTO> itemTable;
    private ItemBO itemBO;
    private ObservableList<ItemDTO> itemDTOS;

    public ManageItemController() {
        itemBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.ITEM);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadColumns();
        loadItems();
    }

    private void loadItems() {
        try {
            ArrayList<ItemDTO> itemDTO = (ArrayList<ItemDTO>) itemBO.getAllItem();
            itemDTOS = FXCollections.observableArrayList(itemDTO);
            itemTable.setItems(itemDTOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadColumns() {
        itemTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        itemTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        itemTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        itemTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));
    }

    @FXML
    void addButtonAction(ActionEvent event) {
        if (checkQty() || checkPrice()) {
            ItemDTO itemDTO = new ItemDTO(itemDescriptionText.getText(), Integer.parseInt(itemQtyText.getText()), Double.parseDouble(itemUnitPriceText.getText()));
            boolean isAdded = false;
            try {
                isAdded = itemBO.addItem(itemDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isAdded) {
                AlertBox.setAlert(Alert.AlertType.INFORMATION, "Item Added");
                loadItems();
            }
        }
    }

    private boolean checkPrice() {
        if (itemUnitPriceText.getText().trim().isEmpty()) {
            AlertBox.setAlert(Alert.AlertType.WARNING, "Item Price is Empty");
            return false;
        } else {
            if (!Validation.integerValueValidate(itemUnitPriceText.getText())) {
                AlertBox.setAlert(Alert.AlertType.WARNING, "Item Price is Invalid");
                return false;
            }
        }
        return true;
    }

    private boolean checkQty() {
        if (itemQtyText.getText().trim().isEmpty()) {
            AlertBox.setAlert(Alert.AlertType.WARNING, "Item Qty is Empty");
            return false;
        } else {
            if (!Validation.integerValueValidate(itemQtyText.getText())) {
                AlertBox.setAlert(Alert.AlertType.WARNING, "Item Qty is Invalid");
                return false;
            }
        }
        return true;
    }

    @FXML
    void dashboardLabalClicked(MouseEvent event) {
        LoadPane.loadPanel("/lk/ijse/pos/view/dashboard.fxml", event, this.getClass());
    }

    @FXML
    void deleteButtonAction(ActionEvent event) {
        boolean isDeleted = false;
        try {
            isDeleted = itemBO.deleteItem(Integer.parseInt(itemCodeText.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isDeleted) {
            AlertBox.setAlert(Alert.AlertType.INFORMATION, "Item Deleted");
            loadItems();
            addButton.setDisable(false);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }

    @FXML
    void itemDescriptionTextAction(ActionEvent event) {
        itemQtyText.requestFocus();
    }

    @FXML
    void itemQtyTextAction(ActionEvent event) {
        itemUnitPriceText.requestFocus();
    }

    @FXML
    void itemTableClicked(MouseEvent event) {
        ItemDTO selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            itemDescriptionText.setText(selectedItem.getItemDescription());
            itemUnitPriceText.setText(selectedItem.getItemUnitPrice() + "");
            itemQtyText.setText(selectedItem.getItemQty() + "");
            itemCodeText.setText(selectedItem.getItemCode() + "");
            addButton.setDisable(true);
            updateButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

    @FXML
    void itemUnitPriceTextAction(ActionEvent event) {
    }

    @FXML
    void updateButtonAction(ActionEvent event) {
        if (checkQty() || checkPrice()) {
            ItemDTO itemDTO = new ItemDTO(Integer.parseInt(itemCodeText.getText()), itemDescriptionText.getText(), Integer.parseInt(itemQtyText.getText()), Double.parseDouble(itemUnitPriceText.getText()));
            boolean isUpdated = false;
            try {
                isUpdated = itemBO.updateItem(itemDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isUpdated) {
                AlertBox.setAlert(Alert.AlertType.INFORMATION, "Item Updated");
                loadItems();
                addButton.setDisable(false);
                updateButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        }
    }
}
