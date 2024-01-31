package lk.ijse.gdse.hello.bo.custom.impl;

import lk.ijse.gdse.hello.bo.custom.ItemBo;
import lk.ijse.gdse.hello.dao.CrudDao;
import lk.ijse.gdse.hello.dao.DAOFactory;
import lk.ijse.gdse.hello.dao.custom.ItemDao;
import lk.ijse.gdse.hello.dto.ItemDTO;
import lk.ijse.gdse.hello.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDAO = (ItemDao) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems= new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll(connection);
        for (Item c : all) {
            allItems.add(new ItemDTO(c.getItemCode(),c.getItemName(),c.getItemPrice(),c.getItemQty()));
        }
        return allItems;
    }

    @Override
    public boolean saveItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(connection,new Item(itemDTO.getItemCode(),itemDTO.getItemName(),itemDTO.getItemPrice(),itemDTO.getItemQty()));
    }

    @Override
    public boolean updateItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(connection,new Item(itemDTO.getItemCode(),itemDTO.getItemName(),itemDTO.getItemPrice(),itemDTO.getItemQty()));
    }

    @Override
    public boolean deleteItem(Connection connection, String itemCode) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(connection,itemCode);
    }
}
