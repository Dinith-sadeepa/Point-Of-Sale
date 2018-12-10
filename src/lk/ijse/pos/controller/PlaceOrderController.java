package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.business.BOFactory;
import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.business.custom.OrdersBO;
import lk.ijse.pos.common.AlertBox;
import lk.ijse.pos.common.LoadPane;
import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.model.*;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {

    @FXML
    private TextField itemDescriptionText;

    @FXML
    private TextField itemQtyText;

    @FXML
    private TextField qtyOnHandText;

    @FXML
    private TextField itemUnitPriceText;

    @FXML
    private TableView<PlaceOrderItemDTO> itemOrderTable;

    @FXML
    private TableColumn<PlaceOrderItemDTO, String> itemDescriptionColumn;

    @FXML
    private TableColumn<PlaceOrderItemDTO, Integer> itemCodeColumn;

    @FXML
    private TableColumn<PlaceOrderItemDTO, Integer> itemQtyColumn;

    @FXML
    private TableColumn<PlaceOrderItemDTO, Double> itemUnitPriceColumn;

    @FXML
    private TextField customerNicText;

    @FXML
    private TextField customerNameText;

    @FXML
    private TextField customerContactText;

    @FXML
    private TextField totalPaymentText;

    @FXML
    private TextField balanceText;

    @FXML
    private TextField payText;

    private CustomerBO customerBO;
    private ItemBO itemBO;
    private OrdersBO ordersBO;


    private ObservableList<PlaceOrderItemDTO> dtos = FXCollections.observableArrayList();
    private double total = 0;

    public PlaceOrderController() {
        itemBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.ITEM);
        customerBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.CUSTOMER);
        ordersBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.ORDERS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadItemOrderTable();
        loadItemDescriptions();
    }

    private void loadItemOrderTable() {
        itemOrderTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        itemOrderTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        itemOrderTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        itemOrderTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));
        itemOrderTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    private void loadItemDescriptions() {
        ArrayList<ItemDTO> itemDTOS = null;
        try {
            itemDTOS = (ArrayList<ItemDTO>) itemBO.getAllItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (itemDTOS != null) {
            ArrayList<String> itemDescriptions = new ArrayList<>();
            for (ItemDTO itemDTO : itemDTOS) {
                itemDescriptions.add(itemDTO.getItemDescription());
            }
            TextFields.bindAutoCompletion(itemDescriptionText, itemDescriptions);
        }
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {

    }

    @FXML
    void customerContactTextAction(ActionEvent event) {

    }

    @FXML
    void customerNameTextAction(ActionEvent event) {

    }

    @FXML
    void customerNicTextAction(ActionEvent event) {
        ArrayList<CustomerDTO> customerDTOS = null;
        try {
            customerDTOS = (ArrayList<CustomerDTO>) customerBO.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (customerDTOS != null) {
            for (CustomerDTO customerDTO : customerDTOS) {
                if (customerDTO.getCustomerNic().equals(customerNicText.getText())) {
                    customerNameText.setText(customerDTO.getCustomerName());
                    customerContactText.setText(customerDTO.getCustomerContact() + "");
                } else {
                    customerNameText.setDisable(false);
                    customerContactText.setDisable(false);
                    customerNameText.requestFocus();
                }
            }
            payText.requestFocus();
        }
    }

    @FXML
    void itemDescriptionTextAction(ActionEvent event) {
        ArrayList<ItemDTO> itemDTOS = null;
        try {
            itemDTOS = (ArrayList<ItemDTO>) itemBO.getAllItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (itemDTOS != null) {
            for (ItemDTO itemDTO : itemDTOS) {
                if (itemDTO.getItemDescription().equals(itemDescriptionText.getText())) {
                    qtyOnHandText.setText(itemDTO.getItemQty() + "");
                    itemUnitPriceText.setText(itemDTO.getItemUnitPrice() + "");
                    break;
                }
            }
            itemQtyText.setDisable(false);
            itemUnitPriceText.setDisable(false);
            itemQtyText.requestFocus();
        }
    }

    @FXML
    void itemQtyTextAction(ActionEvent event) {
        itemUnitPriceText.requestFocus();
        itemUnitPriceText.selectAll();
    }

    @FXML
    void itemUnitPriceTextAction(ActionEvent event) {
        ArrayList<ItemDTO> itemDTOS = null;
        try {
            itemDTOS = (ArrayList<ItemDTO>) itemBO.getAllItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (itemDTOS != null) {
            int id = 0;
            for (ItemDTO itemDTO : itemDTOS) {
                if (itemDTO.getItemDescription().equals(itemDescriptionText.getText())) {
                    id = itemDTO.getItemCode();
                    break;
                }
            }

            int itemQty = Integer.parseInt(itemQtyText.getText());
            double price = Double.parseDouble(itemUnitPriceText.getText());

            PlaceOrderItemDTO placeOrderItemDTO = new PlaceOrderItemDTO(id, itemDescriptionText.getText(), itemQty, price, (itemQty * price));
            dtos.add(placeOrderItemDTO);
            itemOrderTable.setItems(dtos);

            total += itemQty * price;
            totalPaymentText.setText(total + "");
        }
        customerNicText.requestFocus();
    }

    @FXML
    void payTextAction(ActionEvent event) {
        double pay = Double.parseDouble(payText.getText());
        double total = Double.parseDouble(totalPaymentText.getText());

        balanceText.setText((total - pay) + "");
    }

    @FXML
    void placeOrderButtonAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(customerNameText.getText(), customerNicText.getText(), Integer.parseInt(customerContactText.getText()));
        try {
            ArrayList<CustomerDTO> customerDTOS = (ArrayList<CustomerDTO>) customerBO.getAllCustomers();
            if (customerDTOS != null) {
                for (CustomerDTO customerDTO1 : customerDTOS) {
                    if (customerDTO1.getCustomerNic().equals(customerNicText.getText())) {
                        customerDTO = new CustomerDTO(customerDTO1.getCustomerId(), customerDTO1.getCustomerName(), customerDTO1.getCustomerNic(), customerDTO1.getCustomerContact());
                    }
                }
            }

            OrdersDTO ordersDTO = new OrdersDTO();
            ordersDTO.setOrderDate(new Date());
            ordersDTO.setCustomer(customerDTO);


            List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
            ItemDTO itemDTO = new ItemDTO();
            ArrayList<ItemDTO> itemDTOS = (ArrayList<ItemDTO>) itemBO.getAllItem();


            ArrayList<Integer> itemCodes = new ArrayList<>();
            for (PlaceOrderItemDTO placeOrderItemDTO : itemOrderTable.getItems()) {
                itemCodes.add(itemCodeColumn.getCellObservableValue(placeOrderItemDTO).getValue());
            }
            ArrayList<Integer> itemQties = new ArrayList<>();
            for (PlaceOrderItemDTO placeOrderItemDTO : itemOrderTable.getItems()) {
                itemQties.add(itemQtyColumn.getCellObservableValue(placeOrderItemDTO).getValue());
            }
            ArrayList<Double> itemUnitPrices = new ArrayList<>();
            for (PlaceOrderItemDTO placeOrderItemDTO : itemOrderTable.getItems()) {
                itemUnitPrices.add(itemUnitPriceColumn.getCellObservableValue(placeOrderItemDTO).getValue());
            }

            for (Integer itemCodeVal : itemCodes) {
                for (Integer itemQty : itemQties) {
                    for (Double itemprice : itemUnitPrices) {
                        for (ItemDTO itemDTO1 : itemDTOS) {
                            if (itemDTO1.getItemCode() == itemCodeVal) {
                                itemDTO.setItemCode(itemDTO1.getItemCode());
                                itemDTO.setItemDescription(itemDTO1.getItemDescription());
                                itemDTO.setItemQty(itemDTO1.getItemQty());
                                itemDTO.setItemUnitPrice(itemDTO1.getItemUnitPrice());

                                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                                orderDetailDTO.setItem(itemDTO);
                                orderDetailDTO.setOrders(ordersDTO);
                                orderDetailDTO.setOrderQty(itemQty);
                                orderDetailDTO.setPrice(itemprice);
                                orderDetailDTOS.add(orderDetailDTO);
                            }
                        }
                    }
                }
            }

            ordersDTO.setOrderDetails(orderDetailDTOS);
            boolean isAdded = ordersBO.addOrders(ordersDTO);
            if (isAdded) {
                AlertBox.setAlert(Alert.AlertType.INFORMATION, "Order Placed");
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void totalPaymentTextAction(ActionEvent event) {

    }

    @FXML
    void dashboardLabelClicked(MouseEvent event) {
        LoadPane.loadPanel("/lk/ijse/pos/view/dashboard.fxml", event, this.getClass());
    }
}
