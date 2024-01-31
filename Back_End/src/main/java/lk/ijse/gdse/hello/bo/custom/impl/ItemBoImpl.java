package lk.ijse.gdse.hello.bo.custom.impl;

import lk.ijse.gdse.hello.bo.custom.ItemBo;
import lk.ijse.gdse.hello.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(Connection connection, String itemCode) throws SQLException, ClassNotFoundException {
        return false;
    }
}
