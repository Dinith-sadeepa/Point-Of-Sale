package lk.ijse.pos.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String customerName;
    private String customerNic;
    private int customerContact;


    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String customerName, String customerNic, int customerContact) {
        this.customerName = customerName;
        this.customerNic = customerNic;
        this.customerContact = customerContact;
    }

    public Customer(String customerName, String customerNic, int customerContact, List<Orders> orders) {
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

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
