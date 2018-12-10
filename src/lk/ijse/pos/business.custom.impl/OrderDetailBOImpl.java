package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.OrderDetailBO;
import lk.ijse.pos.model.OrderDetailDTO;

import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {
    @Override
    public boolean addOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteOrderDetail(int id) throws Exception {
        return false;
    }

    @Override
    public OrderDetailDTO searchOrderDetail(int id) throws Exception {
        return null;
    }

    @Override
    public List<OrderDetailDTO> getAllOrderDetail() throws Exception {
        return null;
    }
}
