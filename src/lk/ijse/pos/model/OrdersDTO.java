package lk.ijse.pos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersDTO {
    private int orderId;
    private Date orderDate;

    private CustomerDTO customer;
    private List<OrderDetailDTO> orderDetails = new ArrayList<>();

    public OrdersDTO() {
    }

    public OrdersDTO(int orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public OrdersDTO(int orderId, Date orderDate, CustomerDTO customer, List<OrderDetailDTO> orderDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", customer=" + customer +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
