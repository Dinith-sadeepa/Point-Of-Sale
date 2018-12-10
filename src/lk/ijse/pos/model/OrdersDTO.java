package lk.ijse.pos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersDTO {
    private int orderId;
    private Date orderDate;
    private double orderPrice;

    private CustomerDTO customer;
    private List<OrderDetailDTO> orderDetails = new ArrayList<>();
    private PaymentDTO paymentDTO;

    public OrdersDTO() {
    }

    public OrdersDTO(int orderId, Date orderDate, double orderPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
    }

    public OrdersDTO(int orderId, Date orderDate, double orderPrice, CustomerDTO customer, List<OrderDetailDTO> orderDetails, PaymentDTO paymentDTO) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.customer = customer;
        this.orderDetails = orderDetails;
        this.paymentDTO = paymentDTO;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
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
