package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.BOFactory;
import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.business.custom.OrderDetailBO;
import lk.ijse.pos.business.custom.OrdersBO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.entity.Orders;
import lk.ijse.pos.model.ItemDTO;
import lk.ijse.pos.model.OrderDetailDTO;
import lk.ijse.pos.model.OrdersDTO;
import lk.ijse.pos.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private OrderDetailBO orderDetailBO;
    private CustomerBO customerBO;

    public OrdersBOImpl() {
        orderDetailBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.ORDER_DETAIL);
        customerBO = BOFactory.getInstance().getBOTypes(BOFactory.BOTypes.CUSTOMER);
    }

    @Override
    public boolean addOrders(OrdersDTO ordersDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Customer customer;

            if (ordersDTO.getCustomer().getCustomerId() == 0) {
                customer = new Customer();
                customer.setCustomerName(ordersDTO.getCustomer().getCustomerName());
                customer.setCustomerNic(ordersDTO.getCustomer().getCustomerNic());
                customer.setCustomerContact(ordersDTO.getCustomer().getCustomerContact());
            } else {
                customer = session.get(Customer.class, ordersDTO.getCustomer().getCustomerId());
            }

            Orders orders = new Orders();
            orders.setCustomer(customer);
            orders.setOrderDate(ordersDTO.getOrderDate());
            boolean isAddedOrder = (session.save(orders) != null);
            boolean isAdded = false;
            if (isAddedOrder) {
                List<OrderDetail> orderDetails = new ArrayList<>();
                for (OrderDetailDTO orderDetailDTO : ordersDTO.getOrderDetails()) {
                    ItemDTO itemDTO = orderDetailDTO.getItem();
                    Item item = new Item();
                    item.setItemCode(itemDTO.getItemCode());
                    item.setItemDescription(itemDTO.getItemDescription());
                    item.setItemQty(itemDTO.getItemQty());
                    item.setItemUnitPrice(itemDTO.getItemUnitPrice());

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setItem(item);
                    orderDetail.setOrders(orders);
                    orderDetail.setOrderQty(orderDetailDTO.getOrderQty());
                    orderDetail.setPrice(orderDetailDTO.getPrice());

                    isAdded = (session.save(orderDetail) != null);
                }
            }

            session.getTransaction().commit();
            return isAdded;
        }
    }

    @Override
    public boolean updateOrders(OrdersDTO ordersDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteOrders(int id) throws Exception {
        return false;
    }

    @Override
    public OrdersDTO searchOrders(int id) throws Exception {
        return null;
    }

    @Override
    public List<OrdersDTO> getAllOrders() throws Exception {
        return null;
    }
}
