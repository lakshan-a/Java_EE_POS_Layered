package lk.ijse.gdse.hello.dao.custom.impl;

import lk.ijse.gdse.hello.dao.custom.OrderDetailDao;
import lk.ijse.gdse.hello.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public ArrayList<OrderDetail> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Connection connection, OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Connection connection, OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Connection connection, String Id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
