package lk.ijse.pos.model;

public class OrderDetailDTO {

    private int orderDetailId;
    private int orderQty;
    private double price;

    private OrdersDTO orders;
    private ItemDTO item;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderDetailId, int orderQty, double price) {
        this.orderDetailId = orderDetailId;
        this.orderQty = orderQty;
        this.price = price;
    }

    public OrderDetailDTO(int orderDetailId, int orderQty, double price, OrdersDTO orders, ItemDTO item) {
        this.orderDetailId = orderDetailId;
        this.orderQty = orderQty;
        this.price = price;
        this.orders = orders;
        this.item = item;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrdersDTO getOrders() {
        return orders;
    }

    public void setOrders(OrdersDTO orders) {
        this.orders = orders;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderDetailId=" + orderDetailId +
                ", orderQty=" + orderQty +
                ", price=" + price +
                ", orders=" + orders +
                ", item=" + item +
                '}';
    }
}
