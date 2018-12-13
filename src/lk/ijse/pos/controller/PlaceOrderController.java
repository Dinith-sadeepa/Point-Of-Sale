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
import lk.ijse.pos.common.Validation;
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
        clearFields();
    }

    @FXML
    void customerContactTextAction(ActionEvent event) {
        if (Validation.telephoneNoValidate(customerContactText.getText())) {
            payText.requestFocus();
        } else {
            customerContactText.requestFocus();
            customerContactText.selectAll();
        }
    }

    @FXML
    void customerNameTextAction(ActionEvent event) {
        if (Validation.stringsValidate(customerNameText.getText())) {
            customerContactText.requestFocus();
        } else {
            customerNameText.requestFocus();
            customerNameText.selectAll();

        }
    }

    @FXML
    void customerNicTextAction(ActionEvent event) {
        if (Validation.nicValidate(customerNicText.getText())) {

            try {
                ArrayList<CustomerDTO> customerDTOS = (ArrayList<CustomerDTO>) customerBO.getAllCustomers();

                if (customerDTOS != null) {
                    for (CustomerDTO customerDTO : customerDTOS) {
                        if (customerDTO.getCustomerNic().equals(customerNicText.getText())) {
                            customerNameText.setText(customerDTO.getCustomerName());
                            customerContactText.setText(customerDTO.getCustomerContact() + "");
                            payText.requestFocus();
                            break;
                        } else {
                            customerNameText.setDisable(false);
                            customerContactText.setDisable(false);
                            customerNameText.requestFocus();
                        }
                    }
                } else {
                    customerNameText.setDisable(false);
                    customerContactText.setDisable(false);
                    customerNameText.requestFocus();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            AlertBox.setAlert(Alert.AlertType.WARNING, "NIC invalid!");
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
        if (Integer.parseInt(itemQtyText.getText()) <= Integer.parseInt(qtyOnHandText.getText())) {
            itemUnitPriceText.requestFocus();
            itemUnitPriceText.selectAll();
        } else if (Integer.parseInt(itemQtyText.getText()) == 0) {
            itemQtyText.requestFocus();
            itemQtyText.selectAll();
        } else {
            itemQtyText.requestFocus();
            itemQtyText.selectAll();
        }
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

            ArrayList<Integer> itemCodes = new ArrayList<>();
            for (PlaceOrderItemDTO placeOrderItemDTO : itemOrderTable.getItems()) {
                itemCodes.add(itemCodeColumn.getCellObservableValue(placeOrderItemDTO).getValue());
            }
            String error = "ok";
            for (Integer in : itemCodes) {
                if (in == id) {
                    AlertBox.setAlert(Alert.AlertType.WARNING, "This Item was added");
                    error = "error";
                    break;
                }
            }
            if (error.equals("ok")) {
                PlaceOrderItemDTO placeOrderItemDTO = new PlaceOrderItemDTO(id, itemDescriptionText.getText(), itemQty, price, (itemQty * price));
                dtos.add(placeOrderItemDTO);
                itemOrderTable.setItems(dtos);

                total += itemQty * price;
                totalPaymentText.setText(total + "");
            } else {
                itemUnitPriceText.requestFocus();
                itemUnitPriceText.selectAll();
            }
        }
        customerNicText.requestFocus();
    }

    @FXML
    void payTextAction(ActionEvent event) {
        double pay = Double.parseDouble(payText.getText());
        double total = Double.parseDouble(totalPaymentText.getText());
        if (pay < total) {
            AlertBox.setAlert(Alert.AlertType.WARNING, "Please Pay Total Amount");
            payText.requestFocus();
            payText.selectAll();
        } else {
            double balance = (total - pay);
            balanceText.setText(balance + "");
        }
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
                                itemDTO.setItemQty(itemDTO1.getItemQty()-itemQty);
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

            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setOrdersDTO(ordersDTO);
            paymentDTO.setPaymentAmount(Double.parseDouble(payText.getText()));
            paymentDTO.setPaymentDate(new Date());

            ordersDTO.setPaymentDTO(paymentDTO);
            ordersDTO.setOrderPrice(Double.parseDouble(totalPaymentText.getText()));
            boolean isAdded = ordersBO.addOrders(ordersDTO);
            if (isAdded) {
                AlertBox.setAlert(Alert.AlertType.INFORMATION, "Order Placed");
                clearFields();
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

    private void clearFields() {
        itemDescriptionText.setText("");
        qtyOnHandText.setText("");
        itemQtyText.setText("");
        itemUnitPriceText.setText("");
        itemOrderTable.setItems(null);
        customerNameText.setText(null);
        customerContactText.setText(null);
        customerNicText.setText(null);
        totalPaymentText.setText(null);
        payText.setText(null);
        balanceText.setText(null);
    }
}
