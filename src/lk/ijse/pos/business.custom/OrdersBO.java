package lk.ijse.pos.business.custom;

import lk.ijse.pos.business.SuperBO;
import lk.ijse.pos.model.OrdersDTO;

import java.util.List;

public interface OrdersBO extends SuperBO {
    public boolean addOrders(OrdersDTO ordersDTO) throws Exception;

    public boolean updateOrders(OrdersDTO ordersDTO) throws Exception;

    public boolean deleteOrders(int id) throws Exception;

    public OrdersDTO searchOrders(int id) throws Exception;

    public List<OrdersDTO> getAllOrders() throws Exception;
}
