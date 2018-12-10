package lk.ijse.pos.model;

public class PlaceOrderItemDTO {
    private int itemCode;
    private String itemDescription;
    private int itemQty;
    private double itemUnitPrice;
    private double totalPrice;

    public PlaceOrderItemDTO() {
    }

    public PlaceOrderItemDTO(int itemCode, String itemDescription, int itemQty, double itemUnitPrice, double totalPrice) {
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.itemQty = itemQty;
        this.itemUnitPrice = itemUnitPrice;
        this.totalPrice = totalPrice;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
