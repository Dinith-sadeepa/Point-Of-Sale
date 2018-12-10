package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.model.CustomerDTO;
import lk.ijse.pos.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private SessionFactory sessionFactory;

    public CustomerBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            boolean isAdded = (session.save(new Customer(customerDTO.getCustomerName(), customerDTO.getCustomerNic(), customerDTO.getCustomerContact())) != null);
            session.getTransaction().commit();
            return isAdded;
        }
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Customer customer = session.get(Customer.class, customerDTO.getCustomerId());
            customer.setCustomerContact(customerDTO.getCustomerContact());
            customer.setCustomerNic(customerDTO.getCustomerNic());
            customer.setCustomerName(customerDTO.getCustomerName());
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean deleteCustomer(int id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Customer customer = session.get(Customer.class, id);
            session.remove(customer);
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public CustomerDTO searchCustomer(int id) throws Exception {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers;

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            customers = session.createCriteria(Customer.class).list();
            session.getTransaction().commit();
        }
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerNic(), customer.getCustomerContact()));
        }
        return customerDTOS;
    }
}
