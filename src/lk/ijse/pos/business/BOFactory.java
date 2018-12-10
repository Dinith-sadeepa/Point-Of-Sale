package lk.ijse.pos.business;

import lk.ijse.pos.business.custom.impl.CustomerBOImpl;
import lk.ijse.pos.business.custom.impl.ItemBOImpl;
import lk.ijse.pos.business.custom.impl.OrderDetailBOImpl;
import lk.ijse.pos.business.custom.impl.OrdersBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    public enum BOTypes {
        CUSTOMER, ITEM, ORDERS, ORDER_DETAIL
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO> T getBOTypes(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDERS:
                return (T) new OrdersBOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailBOImpl();
            default:
                return null;
        }
    }
}
