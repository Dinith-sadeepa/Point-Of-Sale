package lk.ijse.pos.model;

import java.util.ArrayList;
import java.util.List;

public class ItemDTO {

    private int itemCode;
    private String itemDescription;
    private int itemQty;
    private double itemUnitPrice;

    private List<OrderDetailDTO> orderDetails = new ArrayList<>();

    public ItemDTO() {
    }

    public ItemDTO(int itemCode, String itemDescription, int itemQty, double itemUnitPrice) {
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.itemQty = itemQty;
        this.itemUnitPrice = itemUnitPrice;
    }

    public ItemDTO(String itemDescription, int itemQty, double itemUnitPrice) {
        this.itemDescription = itemDescription;
        this.itemQty = itemQty;
        this.itemUnitPrice = itemUnitPrice;
    }

    public ItemDTO(String itemDescription, int itemQty, double itemUnitPrice, List<OrderDetailDTO> orderDetails) {
        this.itemDescription = itemDescription;
        this.itemQty = itemQty;
        this.itemUnitPrice = itemUnitPrice;
        this.orderDetails = orderDetails;
    }

    public ItemDTO(int itemCode, String itemDescription, int itemQty, double itemUnitPrice, List<OrderDetailDTO> orderDetails) {
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.itemQty = itemQty;
        this.itemUnitPrice = itemUnitPrice;
        this.orderDetails = orderDetails;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemCode=" + itemCode +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemQty=" + itemQty +
                ", itemUnitPrice=" + itemUnitPrice +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
