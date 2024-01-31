package lk.ijse.gdse.hello.bo.custom;

import lk.ijse.gdse.hello.bo.SuperBo;
import lk.ijse.gdse.hello.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends SuperBo {
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException;

    public boolean saveItem(Connection connection ,ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean updateItem(Connection connection,ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(Connection connection,String itemCode) throws SQLException, ClassNotFoundException;

}
