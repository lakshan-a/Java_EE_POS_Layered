package lk.ijse.gdse.hello.dao.custom.impl;

import lk.ijse.gdse.hello.dao.custom.CustomerDao;
import lk.ijse.gdse.hello.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Connection connection, Customer entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Connection connection, Customer entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Connection connection, String Id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
