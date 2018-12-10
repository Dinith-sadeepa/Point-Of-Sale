package lk.ijse.pos.business.custom;

import lk.ijse.pos.business.SuperBO;
import lk.ijse.pos.model.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean addItem(ItemDTO itemDTO) throws Exception;

    public boolean updateItem(ItemDTO itemDTO) throws Exception;

    public boolean deleteItem(int id) throws Exception;

    public ItemDTO searchItem(int id) throws Exception;

    public List<ItemDTO> getAllItem() throws Exception;
}
