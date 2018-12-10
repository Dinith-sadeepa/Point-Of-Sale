package lk.ijse.pos.business.custom;

import lk.ijse.pos.business.SuperBO;
import lk.ijse.pos.model.OrderDetailDTO;

import java.util.List;

public interface OrderDetailBO extends SuperBO {
    public boolean addOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    public boolean updateOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    public boolean deleteOrderDetail(int id) throws Exception;

    public OrderDetailDTO searchOrderDetail(int id) throws Exception;

    public List<OrderDetailDTO> getAllOrderDetail() throws Exception;
}
