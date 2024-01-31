package lk.ijse.gdse.hello.bo.custom;

import lk.ijse.gdse.hello.bo.SuperBo;
import lk.ijse.gdse.hello.dto.CustomerDTO;
import lk.ijse.gdse.hello.dto.ItemDTO;
import lk.ijse.gdse.hello.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBo extends SuperBo {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean purchaseOrder(Connection connection, OrderDTO dto)throws SQLException, ClassNotFoundException;

    public ArrayList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException;

}
