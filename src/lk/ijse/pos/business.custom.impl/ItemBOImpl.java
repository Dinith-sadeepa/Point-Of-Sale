package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.model.ItemDTO;
import lk.ijse.pos.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    private SessionFactory sessionFactory;

    public ItemBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean addItem(ItemDTO itemDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            boolean isAdded = (session.save(new Item(itemDTO.getItemDescription(), itemDTO.getItemQty(), itemDTO.getItemUnitPrice())) != null);
            session.getTransaction().commit();
            return isAdded;
        }
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteItem(int id) throws Exception {
        return false;
    }

    @Override
    public ItemDTO searchItem(int id) throws Exception {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItem() throws Exception {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        List<Item> items;

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            items = session.createCriteria(Item.class).list();
            session.getTransaction().commit();
        }
        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getItemCode(), item.getItemDescription(), item.getItemQty(), item.getItemUnitPrice()));
        }
        return itemDTOS;
    }
}
