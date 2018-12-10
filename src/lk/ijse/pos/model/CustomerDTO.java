package lk.ijse.pos.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {
    private int customerId;
    private String customerName;
    private String customerNic;
    private int customerContact;

    private List<OrdersDTO> orders = new ArrayList<>();

    public CustomerDTO() {
    }

    public CustomerDTO(int customerId, String customerName, String customerNic, int customerContact) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerNic = customerNic;
        this.customerContact = customerContact;
    }

    public CustomerDTO(String customerName, String customerNic, int customerContact) {
        this.customerName = customerName;
        this.customerNic = customerNic;
        this.customerContact = customerContact;
    }

    public CustomerDTO(String customerName, String customerNic, int customerContact, List<OrdersDTO> orders) {
        this.customerName = customerName;
        this.customerNic = customerNic;
        this.customerContact = customerContact;
        this.orders = orders;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public int getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(int customerContact) {
        this.customerContact = customerContact;
    }

    public List<OrdersDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersDTO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerNic='" + customerNic + '\'' +
                ", customerContact=" + customerContact +
                ", orders=" + orders +
                '}';
    }
}
