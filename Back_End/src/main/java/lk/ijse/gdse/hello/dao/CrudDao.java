package lk.ijse.gdse.hello.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao <T> extends SuperDao {
    public ArrayList<T> getAll(Connection connection) throws SQLException, ClassNotFoundException;

    public boolean save(Connection connection,T entity) throws SQLException, ClassNotFoundException;

    public boolean update(Connection connection,T entity) throws SQLException, ClassNotFoundException;

    public boolean delete(Connection connection ,String Id) throws SQLException, ClassNotFoundException;
}
