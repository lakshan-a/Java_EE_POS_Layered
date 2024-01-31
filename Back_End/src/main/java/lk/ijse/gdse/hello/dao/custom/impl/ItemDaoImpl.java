package lk.ijse.gdse.hello.dao.custom.impl;

import lk.ijse.gdse.hello.dao.SQLUtil;
import lk.ijse.gdse.hello.dao.custom.ItemDao;
import lk.ijse.gdse.hello.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public ArrayList<Item> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems = new ArrayList<>();
        ResultSet rst = SQLUtil.execute(connection,"SELECT * FROM item");
        while (rst.next()) {
            Item item = new Item(rst.getString(1), rst.getString(2), rst.getDouble(3),  rst.getInt(4));
            allItems.add(item);
        }
        return allItems;
    }

    @Override
    public boolean save(Connection connection, Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection, "INSERT INTO item VALUES (?,?,?,?)", entity.getItemCode(), entity.getItemName(), entity.getItemPrice(), entity.getItemQty());
    }

    @Override
    public boolean update(Connection connection, Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection, "UPDATE item SET item_name=?, unit_price=?, qty_on_hnd=? WHERE item_ID=?",entity.getItemName() , entity.getItemPrice(), entity.getItemQty(), entity.getItemCode());
    }

    @Override
    public boolean delete(Connection connection, String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection, "DELETE FROM item WHERE item_ID=?", Id);
    }
}
