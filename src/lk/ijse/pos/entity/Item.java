package lk.ijse.pos.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemCode;
    private String itemDescription;
    private int itemQty;
    private double itemUnitPrice;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Item() {
    }

    public Item(String itemDescription, int itemQty, double itemUnitPrice) {
        this.itemDescription = itemDescription;
        this.itemQty = itemQty;
        this.itemUnitPrice = itemUnitPrice;
    }

    public Item(String itemDescription, int itemQty, double itemUnitPrice, List<OrderDetail> orderDetails) {
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

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
