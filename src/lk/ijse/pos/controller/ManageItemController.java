package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.business.BOFactory;
import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.common.AlertBox;
import lk.ijse.pos.common.LoadPane;
import lk.ijse.pos.model.ItemDTO;

import java.net.URL;
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
    private TableView<?> itemTable;
    private ItemBO itemBO;

    public ManageItemController() {
        itemBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.ITEM);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addButtonAction(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO(itemDescriptionText.getText(), Integer.parseInt(itemQtyText.getText()), Double.parseDouble(itemUnitPriceText.getText()));
        boolean isAdded = false;
        try {
            isAdded = itemBO.addItem(itemDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isAdded) {
            AlertBox.setAlert(Alert.AlertType.INFORMATION, "Item Added");
        }
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

    }

    @FXML
    void itemUnitPriceTextAction(ActionEvent event) {
    }

    @FXML
    void updateButtonAction(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO(Integer.parseInt(itemCodeText.getText()), itemDescriptionText.getText(), Integer.parseInt(itemQtyText.getText()), Double.parseDouble(itemUnitPriceText.getText()));
        boolean isUpdated = false;
        try {
            isUpdated = itemBO.updateItem(itemDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isUpdated) {
            AlertBox.setAlert(Alert.AlertType.INFORMATION, "Item Updated");
        }
    }
}
