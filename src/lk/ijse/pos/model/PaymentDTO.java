package lk.ijse.pos.model;

import java.util.Date;

public class PaymentDTO {
    private int paymentId;
    private Date paymentDate;
    private double paymentAmount;

    private OrdersDTO ordersDTO;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentId, Date paymentDate, double paymentAmount) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public PaymentDTO(int paymentId, Date paymentDate, double paymentAmount, OrdersDTO ordersDTO) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.ordersDTO = ordersDTO;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public OrdersDTO getOrdersDTO() {
        return ordersDTO;
    }

    public void setOrdersDTO(OrdersDTO ordersDTO) {
        this.ordersDTO = ordersDTO;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentId=" + paymentId +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                ", ordersDTO=" + ordersDTO +
                '}';
    }
}
