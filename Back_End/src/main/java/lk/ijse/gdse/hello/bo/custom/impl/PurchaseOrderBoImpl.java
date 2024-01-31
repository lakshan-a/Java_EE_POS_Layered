package lk.ijse.gdse.hello.bo.custom.impl;

import lk.ijse.gdse.hello.bo.custom.PurchaseOrderBo;
import lk.ijse.gdse.hello.dao.DAOFactory;
import lk.ijse.gdse.hello.dao.custom.ItemDao;
import lk.ijse.gdse.hello.dao.custom.OrderDao;
import lk.ijse.gdse.hello.dao.custom.OrderDetailDao;
import lk.ijse.gdse.hello.dto.CustomerDTO;
import lk.ijse.gdse.hello.dto.ItemDTO;
import lk.ijse.gdse.hello.dto.OrderDTO;
import lk.ijse.gdse.hello.dto.OrderDetailDTO;
import lk.ijse.gdse.hello.entity.OrderDetail;
import lk.ijse.gdse.hello.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBoImpl implements PurchaseOrderBo {

    OrderDao orderDAO= (OrderDao) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDao orderDetailsDAO = (OrderDetailDao) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    ItemDao itemDAO = (ItemDao) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean purchaseOrder(Connection connection, OrderDTO dto) throws SQLException, ClassNotFoundException {
        connection.setAutoCommit(false);
        if (orderDAO.save(connection,new Orders(dto.getOrderID(),dto.getDate(),dto.getCustomerID()))){
            for (OrderDetailDTO orderDetailsDTO: dto.getOrderDetailsDTOList()) {
                if (orderDetailsDAO.save(connection,new OrderDetail(orderDetailsDTO.getItemCode(),orderDetailsDTO.getOrderID(),orderDetailsDTO.getQty(),orderDetailsDTO.getUnitPrice()))){
                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                }else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
        }
        connection.rollback();
        connection.setAutoCommit(true);
        return false;

    }

    @Override
    public ArrayList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> allOrders= new ArrayList<>();
        ArrayList<Orders> all = orderDAO.getAll(connection);
        for (Orders c : all) {
            allOrders.add(new OrderDTO(c.getOrderID(),c.getDate(),c.getCustomerID()));
        }
        return allOrders;
    }
}
